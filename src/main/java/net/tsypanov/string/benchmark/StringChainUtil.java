package net.tsypanov.string.benchmark;

import net.tsypanov.string.StringChain;

final class StringChainUtil {

  static String concatWithStringBuilder(String... stringArray) {
    StringBuilder sb = new StringBuilder();
    for (String str : stringArray) {
      sb.append(str);
    }
    return sb.toString();
  }

  static String concatWithStringChain(String... stringArray) {
    StringChain sc = new StringChain(stringArray.length);
    for (String str : stringArray) {
      sc.add(str);
    }
    return sc.toString();
  }
}
