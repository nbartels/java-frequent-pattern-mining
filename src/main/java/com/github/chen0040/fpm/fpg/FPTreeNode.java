package com.github.chen0040.fpm.fpg;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xschen on 8/2/2015.
 */
@Setter
@Getter
public class FPTreeNode implements Serializable {

   private static final long serialVersionUID = 3209454955222377729L;
   private String item;
   private int count = 0;

   private FPTreeNode parent = null;

   private List<FPTreeNode> children = new ArrayList<>();

   public FPTreeNode(String item, int count) {
      this.item = item;
      this.count = count;
   }

   public FPTreeNode(){

   }


    public void addChild(FPTreeNode node)
   {
      node.parent = this;
      children.add(node);
   }

   public FPTreeNode child(int i)
   {
      return children.get(i);
   }

   public int childCount()
   {
      return children.size();
   }

   public boolean isLeaf()
   {
      return children.isEmpty();
   }

   public boolean isRoot()
   {
      return parent == null;
   }


   public FPPath getPath() {
      FPPath path = new FPPath();
      path.setSinglePrefix(true);
      FPTreeNode x = this;
      while (x != null) {
         if (!x.isRoot()) {
            if(x.childCount() > 1){
               path.setSinglePrefix(false);
            }
            path.add(x);
         }
         x = x.parent;
      }

      return path;
   }

   public boolean removeChild(FPTreeNode node)
   {
      return children.remove(node);
   }


    public void incCount(int incr) {
      count+=incr;
   }

   public void print(String meta) {
      System.out.println(meta);
      print("", true);
   }

   private void print(String prefix, boolean isTail) {
      System.out.println(prefix + (isTail ? "└── " : "├── ") + "(" + item + ", " + count + ")");
      for (int i = 0; i < children.size() - 1; i++) {
         children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
      }
      if (!children.isEmpty()) {
         children.get(children.size() - 1)
                 .print(prefix + (isTail ?"    " : "│   "), true);
      }
   }



}
