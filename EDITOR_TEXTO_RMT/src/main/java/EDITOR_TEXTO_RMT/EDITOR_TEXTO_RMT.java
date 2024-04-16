package EDITOR_TEXTO_RMT;

import javax.swing.*; // paleta de colores
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

// Esta es la clase princiapal que indica el editor de texto
public class EDITOR_TEXTO_RMT extends JFrame {

    // Estos son los componentes

    // Panel texto
    private JTextPane panelTexto;

    // Boton negrita
    private JButton botonNegrita;

    // Boton cursiva
    private JButton botonCursiva;

    // Boton Subrayado
    private JButton botonSubrayado;

    // Hemos utilizado un ComboBox (LISTA DESPLEGABLE) para poder determinar un tamaño de letra
    private JComboBox<String> comboBoxTamanio;

    // Hemos utilizado un Combobox (LISTA DESPLEGABLE) para poder determinar la fuente del texto
    private JComboBox<String> comboBoxFuente;

    // Boton para seleccionar un color mediante unas paletas de colores
    private JButton botonColor;

    public EDITOR_TEXTO_RMT() {
        // Titulo del editor (Ventana)
        setTitle("Editor de Texto del RMT");
        // Tamaño del editor de texto
        setSize(800, 800);
        // Accion al cerrar la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelTexto = new JTextPane();

        // Botón de negrita
        botonNegrita = new JButton("Negrita");
        // Botón de cursiva
        botonCursiva = new JButton("Cursiva");
        // Botón de subrayado
        botonSubrayado = new JButton("Subrayado");
        // Botón de color
        botonColor = new JButton("Color");

        // Creamos el ComboBox para el tamaño de la letra
        String[] tamanios = {"10", "12", "14", "16", "18", "20", "24", "42"};
        comboBoxTamanio = new JComboBox<>(tamanios);
        // Tamaño por defecto
        comboBoxTamanio.setSelectedIndex(20);

        // Creamos el  ComboBox para el estilo de la fuente
        String[] fuentes = {"Arial", "Times New Roman", "Courier New"};
        comboBoxFuente = new JComboBox<>(fuentes);

        // Con esto asignamos los listeners a los componenetes
        botonNegrita.addActionListener(new BoldButtonListener());
        botonCursiva.addActionListener(new ItalicButtonListener());
        botonSubrayado.addActionListener(new UnderlineButtonListener());
        botonColor.addActionListener(new ColorButtonListener());
        comboBoxTamanio.addActionListener(new SizeComboBoxListener());
        comboBoxFuente.addActionListener(new FontComboBoxListener());

        // Aqui se indica la configuracion del editor de texto
        JScrollPane scrolTexto = new JScrollPane(panelTexto);

        JPanel panelBoton = new JPanel();
        // Se agragan todos los botones que hemos creado
        panelBoton.add(botonNegrita);
        panelBoton.add(botonCursiva);
        panelBoton.add(botonSubrayado);
        panelBoton.add(comboBoxTamanio);
        panelBoton.add(comboBoxFuente);
        panelBoton.add(botonColor);

        // Aqui podemos configurar la posicion de los botones en este caso lo hemos puesto centrado en la barra superior
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrolTexto, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.NORTH);
    }

    // BOTON NEGRITA
    private class BoldButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Obtener la selección actual
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            // Obtener el documento de estilo del JTextPane
            StyledDocument doc = panelTexto.getStyledDocument();

            // Crear un nuevo estilo
            Style style = doc.addStyle("Negrita", null);

            // Verificar si el texto seleccionado está en negrita
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esNegrita = StyleConstants.isBold(atributos);

            // Alternar el estilo de negrita
            if (esNegrita) {
                // Si el texto seleccionado está en negrita, quitar el estilo de negrita
                StyleConstants.setBold(style, false);
            } else {
                // Si el texto seleccionado no está en negrita, aplicar el estilo de negrita
                StyleConstants.setBold(style, true);
            }

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    // BOTON CURSIVA
    private class ItalicButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Obtener la selección actual
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            // Obtener el documento de estilo del JTextPane
            StyledDocument doc = panelTexto.getStyledDocument();

            // Crear un nuevo estilo
            Style style = doc.addStyle("Cursiva", null);

            // Verificar si el texto seleccionado está en cursiva
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esCursiva = StyleConstants.isItalic(atributos);

            // Alternar el estilo de cursiva
            if (esCursiva) {
                // Si el texto seleccionado está en cursiva, quitar el estilo de cursiva
                StyleConstants.setItalic(style, false);
            } else {
                // Si el texto seleccionado no está en cursiva, aplicar el estilo de cursiva
                StyleConstants.setItalic(style, true);
            }

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    // BOTON SUBRAYADO
    private class UnderlineButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Obtener la selección actual
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            // Obtener el documento de estilo del JTextPane
            StyledDocument doc = panelTexto.getStyledDocument();

            // Crear un nuevo estilo
            Style style = doc.addStyle("Subrayado", null);

            // Verificar si el texto seleccionado está subrayado
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esSubrayado = StyleConstants.isUnderline(atributos);

            // Alternar el estilo de subrayado
            if (esSubrayado) {
                // Si el texto seleccionado está subrayado, quitar el estilo de subrayado
                StyleConstants.setUnderline(style, false);
            } else {
                // Si el texto seleccionado no está subrayado, aplicar el estilo de subrayado
                StyleConstants.setUnderline(style, true);
            }

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    // BOTON COLOR
    private class ColorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Mostrar un diálogo de selección de color
            Color color = JColorChooser.showDialog(EDITOR_TEXTO_RMT.this, "Seleccionar Color de Texto", Color.BLACK);
            if (color != null) {
                // Obtener la selección actual
                int start = panelTexto.getSelectionStart();
                int end = panelTexto.getSelectionEnd();

                // Obtener el documento de estilo del JTextPane
                StyledDocument doc = panelTexto.getStyledDocument();

                // Crear un nuevo estilo
                Style style = doc.addStyle("Color", null);

                // Aplicar el color seleccionado al estilo
                StyleConstants.setForeground(style, color);

                // Aplicar el estilo al texto seleccionado
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        }
    }

    // BOTON TAMAÑO
    private class SizeComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Obtener el tamaño seleccionado del ComboBox
            String selectedSize = (String) comboBoxTamanio.getSelectedItem();
            int size = Integer.parseInt(selectedSize);

            // Obtener la selección actual
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            // Obtener el documento de estilo del JTextPane
            StyledDocument doc = panelTexto.getStyledDocument();

            // Crear un nuevo estilo
            Style style = doc.addStyle("Tamaño", null);

            // Aplicar el tamaño seleccionado al estilo
            StyleConstants.setFontSize(style, size);

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    // BOTON FUENTE
    private class FontComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Obtener la fuente seleccionada del ComboBox
            String selectedFont = (String) comboBoxFuente.getSelectedItem();

            // Obtener la selección actual
            int start = panelTexto.getSelectionStart();
            int end = panelTexto.getSelectionEnd();

            // Obtener el documento de estilo del JTextPane
            StyledDocument doc = panelTexto.getStyledDocument();

            // Crear un nuevo estilo
            Style style = doc.addStyle("Fuente", null);

            // Aplicar la fuente seleccionada al estilo
            StyleConstants.setFontFamily(style, selectedFont);

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    public static void main(String[] args) {
        EDITOR_TEXTO_RMT editor = new EDITOR_TEXTO_RMT();
        editor.setVisible(true);
    }
}