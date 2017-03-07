package fr.loulouw.louwchat;

import java.util.ArrayList;

/**
 * Created by Loulouw on 27/02/2017.
 */
public class PersonnalList<T> {

    private ArrayList<T> list;
    private PlayerHologram ph;


    public PersonnalList(PlayerHologram ph){
        list = new ArrayList<>();
        this.ph = ph;
    }

    public void add(T t) {
        list.add(t);
        ph.changement();
    }

    public void remove(T t){
        list.remove(t);
        ph.changement();
    }

    public void remove(int index){
        list.remove(index);
        ph.changement();
    }

    public int size(){
        return list.size();
    }

    public ArrayList<T> getList() {
        return list;
    }
}
