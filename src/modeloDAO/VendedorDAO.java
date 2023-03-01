package modeloDAO;

import java.io.*;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import modelo.Vendedor;

public class VendedorDAO {

    LinkedList<Vendedor> lista = new LinkedList<Vendedor>();

    public DefaultTableModel listar() {
        cargar();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Caja");
        model.addColumn("Ventas");
        model.addColumn("Genero");

        Object[] fila = new Object[5];

        for (Vendedor v : lista) {
            fila[0] = v.getCodigo();
            fila[1] = v.getNombre();
            fila[2] = v.getCaja();
            fila[3] = v.getVentas();
            fila[4] = v.getGenero();
            model.addRow(fila);
        }

        return model;
    }

    public void add(Vendedor vendedor) {
        cargar();
        lista.add(vendedor);
        guardar();
    }

    //NO LLEVA CARGAR
    public Vendedor serch(int codigo) {
        int i = 0;
        Vendedor vendedor = null;
        for (Vendedor v : lista) {
            if (v.getCodigo() == codigo) {
                vendedor = v;
                break;
            }
            i++;
        }
        return vendedor;
    }

    public void editar(Vendedor vendedor) {
        cargar();
        int codigo = vendedor.getCodigo();
        int i = 0;
        for (Vendedor v : lista) {
            if (v.getCodigo() == codigo) {
                break;
            }
            i++;
        }
        lista.set(i, vendedor);
        guardar();  
    }

    //NO LLEVA CARGAR
    public void delete(int codigo) {
        int i = 0;
        for (Vendedor v : lista) {
            if (v.getCodigo() == codigo) {
                break;
            }
            i++;
        }
        lista.remove(i);
        guardar();
    }

    public void guardar() {
        try {
            FileOutputStream fileOut = new FileOutputStream("lista.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(lista);
            out.close();
            fileOut.close();
            System.out.println("La lista se ha serializado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargar() {
        try {
            FileInputStream fileIn = new FileInputStream("lista.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            //tomamos la lista ya creada
            lista = (LinkedList<Vendedor>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("La lista se ha deserializado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
