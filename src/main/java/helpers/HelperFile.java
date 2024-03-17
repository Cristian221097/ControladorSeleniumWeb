package helpers;

import java.io.File;

public class HelperFile {

    private static HelperFile instance = null;

    private HelperFile() {
    }

    public static HelperFile getInstance() {
        if (instance == null) {
            instance = new HelperFile();
        }
        return instance;
    }

    public boolean isExitFile(String path, String name) {
        boolean result = false;
        try {
            File file = new File(path, name);
            result = file.exists();
        } catch (Exception err) {
            System.out.println("Error al confirmar si el archivo:" + name + " existe" + err.getMessage());
        }

        return result;
    }

    public String[] getNameFiles(String path) {
        String[] result = new String[0];
        try {
            File file = new File(path);

           result = file.list();
        }catch (Exception err){
            System.out.println("Error al confirmar la lista de archivos en la ryta:" + path+" " + err.getMessage());
        }
        return  result;
    }

}
