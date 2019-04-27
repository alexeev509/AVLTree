package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class AvlTree<V> {

    private Entry<V> root=null;

    Comparator comparator;

    public AvlTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public static class Entry<V>{
        V value;
        Entry<V> right;
        Entry<V> left;
        Entry<V> parent;
        int balance=0;

        public Entry(V value, Entry<V> parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    public V remove(V value){
        Entry<V> parent=null;
        Entry<V> t=root;
        while (t!=null){
            parent=t;
            if(comparator.compare(value,t.value)>0)
                t=t.right;
            else if(comparator.compare(value,t.value)<0)
                t=t.left;
            else {
                break;
            }
        }
        if(t==null)
            return value;
        if(t.left==null && t.right==null){
            if(t.parent.right==t)
                t.parent.right=null;
            else
                t.parent.left=null;
            t=null;
        }else {
                    Entry<V> nodeForChangeValues=t.right;
       while (nodeForChangeValues.left!=null)
           nodeForChangeValues=nodeForChangeValues.left;

       t.value=nodeForChangeValues.value;

       if(nodeForChangeValues.parent.left==nodeForChangeValues)
           nodeForChangeValues.parent.left=null;
       else
           nodeForChangeValues.parent.right=null;
       nodeForChangeValues=null;

        }
        fixAfterDelition(t);
        return value;
    }

    private void fixAfterDelition(Entry<V> entry){

    }

    public V add(V value){
        Entry<V> parent=null;
       Entry<V> t=root;
       if(root==null)
           root=new Entry<>(value,null);
       else {
           //find place for insert
           while (t!=null){
               parent=t;
               if(comparator.compare(value,t.value)>0)
                   t=t.right;
               else if(comparator.compare(value,t.value)<0)
                   t=t.left;
               else {
                   t.value = value;
                   return value;
               }
           }
           Entry<V> current=new Entry<>(value,parent);


           if(comparator.compare(value,parent.value)>0)
               parent.right = current;
           else
               parent.left = current;

           fixAfterInsertion(current);


       }
        return value;
    }

    private void fixAfterInsertion(Entry<V> p) {
           Entry<V> current=p;
          while (current!=root){
              //Определяем слева или справа добавился элемент
              if(comparator.compare(parentOf(current).value,current.value)>0) {
                  --parentOf(current).balance;
                  if(parentOf(current).balance==0)
                      break;
              }
              else {
                  ++parentOf(current).balance;
                  if(parentOf(current).balance==0)
                      break;
              }

              current=parentOf(current);

              if(current.balance==2){
                  //Это двойной поворот
                  if(current.right.balance<0){
                      //пЕРЕД ПОВОРОТОМ ИДЕТ РАССТАНОВКА БАЛАНСОВ
                      //Она зависит от сына правого узла
                      if(current.right.left.balance==0){
                          current.balance=0;
                          current.right.balance=0;
                          current.right.left.balance=0;
                      }
                      else if(current.right.left.balance==1){
                          current.balance=-1;
                          current.right.balance=0;
                          current.right.left.balance=0;
                      }
                      else if(current.right.left.balance==-1) {
                          current.balance=0;
                          current.right.balance=1;
                          current.right.left.balance=0;
                      }
                      rotateRight(current.right);
                      rotateLeft(current);
                  }
                  //Это один поворот
                  else {
                          current.balance=0;
                          current.right.balance=0;
                      rotateLeft(current);
                  }
                  break;

              }
              else if(current.balance==-2){
                  if(current.left.balance>0) {

                      if(current.left.right.balance==0){
                          current.balance=0;
                          current.left.balance=0;
                          current.left.right.balance=0;
                      }
                      else  if(current.left.right.balance==1){
                          current.balance=0;
                          current.left.balance=-1;
                          current.left.right.balance=0;
                      }
                      else  if(current.left.right.balance==-1){
                          current.balance=1;
                          current.left.balance=0;
                          current.left.right.balance=0;
                      }

                      rotateLeft(current.left);
                      rotateRight(current);
                  }
                 else {
                          current.balance = 0;
                          current.left.balance = 0;
                      rotateRight(current);
                  }
                  break;
              }

          }
    }

    private void rotateLeft(Entry<V> p){
        if(p!=null) {
            Entry<V> t = p.right;
            p.right=t.left;
            if(t.left!=null)
                t.left.parent=p;
            t.parent=p.parent;
            if(p.parent==null)
                root=t;
            else if(p.parent.left==p)
                p.parent.left=t;
            else
                p.parent.right=t;
            t.left=p;
            p.parent=t;
        }
    }


    private void rotateRight(Entry<V> p){
        if(p!=null) {
            Entry<V> t =p.left;
            p.left=t.right;
            if(t.right!=null)
                t.right.parent=p;
            t.parent=p.parent;
            if(p.parent==null)
                root=t;
            else if(p.parent.left==p)
                p.parent.left=t;
            else
                p.parent.right=t;

            t.right=p;
            p.parent=t;
        }
    }


    private static <V> Entry<V> parentOf(Entry<V> p) {
        return (p == null ? null: p.parent);
    }



    ArrayList<V> listOfValues;
    public ArrayList<V> getListOfValues(){
        listOfValues=new ArrayList<>();
        getValues(root);
        return listOfValues;
    }
    public void getValues(Entry<V> entry){
        if(entry!=null) {
            listOfValues.add(entry.value);
            getValues(entry.left);
            getValues(entry.right);
        }
    }


    ArrayList<Integer> listOfBalances;
    public ArrayList<Integer> getListOfBalances(){
        listOfBalances=new ArrayList<>();
        getBalances(root);
        return listOfBalances;
    }
    public void getBalances(Entry<V> entry){
        if(entry!=null) {
            listOfBalances.add(entry.balance);
            getBalances(entry.left);
            getBalances(entry.right);
        }
    }
}
