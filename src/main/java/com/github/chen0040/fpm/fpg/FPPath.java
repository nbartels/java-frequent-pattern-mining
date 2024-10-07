package com.github.chen0040.fpm.fpg;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


/**
 * Created by xschen on 8/2/2015.
 */
@Setter
@Getter
public class FPPath extends ArrayList<FPTreeNode> {
   private boolean singlePrefix = false;
}
