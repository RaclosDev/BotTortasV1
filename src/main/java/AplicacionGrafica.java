import javax.swing.*;

public class AplicacionGrafica extends JFrame{

    /**
     * Panel de la aplicacion
     */
    private JPanel contentPane;
    public AplicacionGrafica(){

        //Añade un titulo, no es estrictamente necesario
        setTitle("El tortas bot - by raclos");

        /*
         * Coordenadas x y de la aplicacion y su altura y longitud,
         * si no lo indicamos aparecera una ventana muy pequeña
         */
        setBounds(400, 200, 350, 100);

        /*
         * Indica que cuando se cierre la ventana se acaba la aplicacion,
         * si no lo indicamos cuando cerremos la ventana la aplicacion seguira funcionando
         */
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Hace visible la ventana, si no lo hacemos no veremos la aplicacion
        setVisible(true);

        //Creamos el panel
        contentPane =new JPanel();

        //Indicamos su diseño
        contentPane.setLayout(null);

        //asigno el pannel a la ventana
        setContentPane(contentPane);
        JButton salirButton = new JButton("Destortear");
        contentPane.add(salirButton);

    }
}