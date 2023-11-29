import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TelaDados extends JDialog {


    private JPanel jPanel = new JPanel(new GridBagLayout()); /* Painel de componentes */

    private JLabel descricaoHora = new JLabel("Data e Hora 1");
    private JTextField descricaoData = new JTextField();

    private JLabel descricaoHora2 = new JLabel("Data e Hora 2 ");
    private JTextField descricaoData2 = new JTextField();

    private JButton jButton = new JButton("Start");
    private JButton jButton2 = new JButton("Stop");

    private Runnable thread1 = new Runnable() {
        @Override
        public void run() {
            while (true) {
                descricaoData.setText(new SimpleDateFormat("dd/MM/yyy hh:mm.ss").
                        format(Calendar.getInstance().getTime()));

                descricaoData2.setText(new SimpleDateFormat("dd-MM-yyy hh:mm.ss").
                        format(Calendar.getInstance().getTime()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Thread thread1Hora;



    public TelaDados(){
        setTitle("DATA E HORA");
        setSize(new Dimension(240, 240));
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints(); /* Controlador de posicionamento */
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 10 ,5, 5);
        gridBagConstraints.anchor = GridBagConstraints.WEST;


        /* Data e hora 1 */
        descricaoHora.setPreferredSize(new Dimension(200, 25));
        jPanel.add(descricaoHora, gridBagConstraints);

        descricaoData.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy ++;
        descricaoData.setEditable(false);
        jPanel.add(descricaoData, gridBagConstraints);

        /* Data e hora 2 */
        descricaoHora2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy ++;
        jPanel.add(descricaoHora2, gridBagConstraints);

        descricaoData2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy ++;
        descricaoData2.setEditable(false);
        jPanel.add(descricaoData2, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;


        /* Botão Start */
        jButton.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridy ++;
        jPanel.add(jButton, gridBagConstraints);

       jButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { /* Executa clique no botão */
               thread1Hora = new Thread(thread1);
               thread1Hora.start();

               jButton.setEnabled(false);
               jButton2.setEnabled(true);

           }
       });


        /* Botão Stop */
        jButton2.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridx ++;
        jPanel.add(jButton2, gridBagConstraints);

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thread1Hora.stop();

                jButton.setEnabled(true);
                jButton2.setEnabled(false);
            }
        });

        jButton2.setEnabled(false);

        add(jPanel, BorderLayout.WEST);
        setVisible(true); /* Torna a tela visivel para   o usuario */
    }
}
