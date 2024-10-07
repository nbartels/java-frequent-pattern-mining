package com.github.chen0040.fpm.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by xschen on 8/2/2015.
 */
@Setter
@Getter
public class ItemSet {
   private List<String> items = new ArrayList<>();
   private int parentSupport;
   private int support;

   public ItemSet(int count){
      support = count;
   }

   public ItemSet(){

   }


    @Override
   public String toString(){
      return "{" + String.join(", ", items) + "} (support: " + getSupport() + ")";
   }


   public int countItems() {
      return items.size();
   }


   public String getItemAt(int k) {
      return items.get(k);
   }


   public boolean containsItem(String item) {
      return items.contains(item);
   }

   public void copy(ItemSet rhs){
      items.clear();

      for(int i=0; i < rhs.items.size(); ++i) {
         items.add(rhs.items.get(i));
      }
      support = rhs.support;
      parentSupport = rhs.parentSupport;
   }


   public ItemSet makeCopy() {
      ItemSet clone = new ItemSet();
      clone.copy(this);
      return clone;
   }


    public String lastItem() {
      return items.get(items.size()-1);
   }


   public void incSupport() {
      support++;
   }


   public void addAll(List<String> items) {
      this.items.addAll(items);
   }


   @Override public int hashCode() {
      int hash = 3001;
      for(int i=0; i < countItems(); ++i){
         hash = hash * 31 + items.get(i).hashCode();
      }
      hash = hash * 31 + support;
      return hash;
   }

   @Override public boolean equals(Object obj) {
      if(!(obj instanceof ItemSet)){
         return false;
      }

      ItemSet rhs = (ItemSet)obj;

      if(countItems() != rhs.countItems()) {
         return false;
      }

      for(int i=0; i < countItems(); ++i) {
         if(!items.get(i).equals(rhs.items.get(i))){
            return false;
         }
      }

      return true;
   }


   public void addItem(String item) {
      items.add(item);
   }

}
