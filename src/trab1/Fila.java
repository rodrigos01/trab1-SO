/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1;

import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author rodrigo
 */
public class Fila extends LinkedList<Aluno>{

    DefaultListModel<Aluno> model;
    
    @Override
    public boolean add(Aluno e) {
        if(model != null)
            model.addElement(e);
        
        return super.add(e); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public Aluno remove() {
        if(model != null)
            model.remove(0);
        return super.remove(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        if(model != null)
            model.removeElement(o);
        return super.remove(o); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public void setModel(DefaultListModel<Aluno> model) {
        this.model = model;
    }
    
}
