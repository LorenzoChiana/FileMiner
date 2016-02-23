package fileminer.main;

import javax.swing.UIManager;

import fileminer.controller.ControllerImpl;

/**
 * 
 * @author Lorenzo Chiana
 *      class that contains the main
 *
 */
public class FileMinerApp { 
    /**
     * 
     * @param args
     *      param in main
     */
    public static void main(final String[] args) {
        useSystemLookAndFeel(true);
        new ControllerImpl();
//        Chronology cr = new ChronologyImpl();
//        cr.addDirectory("1");
//        cr.addDirectory("2");
//        cr.addDirectory("3");
//        cr.addDirectory("5");
//        cr.addDirectory("6");
//        cr.addDirectory("7");
//        System.out.println(cr.getCurrentDirectory());
//        System.out.println(cr.getChronology());
//        cr.prevDir(); //6
//        System.out.println(cr.getCurrentDirectory());
//        cr.prevDir(); //5
//        System.out.println(cr.getCurrentDirectory());
//        cr.prevDir(); //3
//        System.out.println(cr.getCurrentDirectory());
//        cr.prevDir(); //2
//        System.out.println(cr.getCurrentDirectory());
//        cr.prevDir(); //1
//        System.out.println(cr.getCurrentDirectory());
//        cr.prevDir(); //0
//        System.out.println(cr.getCurrentDirectory());
//        cr.prevDir(); //-1 -> 0
//        System.out.println(cr.getCurrentDirectory());
//        cr.nextDir(); //1
//        System.out.println(cr.getCurrentDirectory());
//        cr.addDirectory("4");
//        System.out.println(cr.getChronology());
//        
    }

    private static void useSystemLookAndFeel(final boolean flag) {
        if (flag) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}