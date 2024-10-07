package com.github.chen0040.fpm.data;

import com.github.chen0040.fpm.utils.CollectionUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by xschen on 8/2/2015.
 */
@Setter
@Getter
public class ItemSets {
   private List<ItemSet> sets = new ArrayList<>();

   public ItemSets addAll(List<ItemSet> fis) {
      sets.addAll(fis);
      return this;
   }

   public Stream<ItemSet> stream(){
      return sets.stream();
   }

   public int countSets() {
      return sets.size();
   }

   public ItemSet getItemSet(int i) {
      return sets.get(i);
   }

   public ItemSet removeItemSetAt(int i) {
      return sets.remove(i);
   }

   public Set<String> generateCombinations(){
      Set<String> result = new HashSet<>();
      for(int i=0; i < countSets(); ++i){
         ItemSet itemSet = getItemSet(i);
         List<String> items = itemSet.getItems();
         List<List<String>> combinations = CollectionUtils.generateCombinations(items);
         for(List<String> combination : combinations) {
            combination.sort(String::compareTo);
            result.add(String.join(", ", combination));
         }
      }
      return result;
   }

   public boolean isSubsetOf(ItemSets rhs) {
      Set<String> allsets = generateCombinations();
      Set<String> rhssets = rhs.generateCombinations();
      for(String item : allsets) {
         if(!rhssets.contains(item)){
            return false;
         }
      }
      return true;
   }


   public String getSignature() {
      List<String> items = new ArrayList<>(generateCombinations());
      items.sort(String::compareTo);
      return "(" + String.join(")\r\n(", items) + ")";

   }
}
