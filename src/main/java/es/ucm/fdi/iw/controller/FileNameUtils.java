package es.ucm.fdi.iw.controller;

public class FileNameUtils {

    /**
    * Obtener sufijo de archivo
    * @param fileName
    * @return
    */
   public static String getSuffix(String fileName){
       return fileName.substring(fileName.lastIndexOf("."));
   }

   /**
          * Generar nuevo nombre de archivo
    * @param  fileOriginName nombre del archivo fuente
    * @return
    */
   public static String getFileName(String fileOriginName){
       return  FileNameUtils.getSuffix(fileOriginName);
   }


}