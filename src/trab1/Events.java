/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1;

import javax.swing.DefaultListModel;

/**
 *
 * @author rodrigo
 */
public class Events {
    
    public static DefaultListModel<String> model = new DefaultListModel<>();
    
    public static void add(String s) {
        Events.model.add(0, s);
        //Backup porque o swing buga
        System.out.println(s);
    }
    
}
