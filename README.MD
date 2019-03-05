# Улучшенное склеивание строк со StringChain | Enhanced String concatenation using StringChain 

Scroll down to read [English version](#in-english)

## По-русски

Данный проект содержит класс `StringChain`, которы можно использовать вместо `java.lang.StringBuilder` для достижения
наивысшей производительности особенно в тех случаях, когда все склеиваемые элементы являются `CharSequence` и известно их
количество.

Обычно мы склеиваем строки следующим образом:
```java
StringBuilder sb = new StringBuilder();
for (CharSequence cs : charSequences) {
  sb.append(cs);
}
String result =  sb.toString():

```
Иногда для этого можно использовать `java.util.StringJoiner`:
```java
StringJoiner sj = new StringJoiner("");
for (CharSequence cs : charSequences) {
  sj.append(cs);
}
String result =  sj.toString():
```
равно как
```java
String result = String.join("", charSequences);
```

У обоих подходов есть недостатки:
- `StringBuilder` содержит внутри `char[]`, при чём его итоговый размер далеко не всегда известен или может быть легко вычислен.
- `StringJoiner` содержит код, отвечающий за префикс, суффикс и инфикс, которые не нужны при склейке.

Поэтому создан новый класс `StringChain`, являющийся в основном копией `java.util.StringJoiner` без префикса, суффикса и инфикса.
Вместо этого `StringChain` может быть создан с массивом известной длины в тех случаях, когда количество слагаемых строк
известно. Это позволяет избежать повторного создания массива и копирования его данных.

Результаты замеров с помощью `StringBuilderVsStringChainBenchmark`, выполненных на моей рабочей машине (Intel i7-7700, JDK11)
можно найти в файле [./results/StringBuilderVsStringChainBenchmark.txt](./results/StringBuilderVsStringChainBenchmark.txt)

## In English

This project introduces `StringChain` which can be used instead of standard `java.lang.StringBuilder` to achieve ultimate
performance particularly in cases when all concatenated elements are of type `CharSequence` and their count is known at 
runtime.

Usually we do this to concatenate multiple `CharSequence`:
```java
StringBuilder sb = new StringBuilder();
for (CharSequence cs : charSequences) {
  sb.append(cs);
}
String result =  sb.toString():

```
Also `java.util.StringJoiner` can be employed:
```java
StringJoiner sj = new StringJoiner("");
for (CharSequence cs : charSequences) {
  sj.append(cs);
}
String result =  sj.toString():
```
which equals to
```java
String result = String.join("", charSequences);
```

Both approaches has disadvantages:
- `StringBuilder` is built around `char[]` and its size is often unknown or cannot be easily calculated
- `StringJoiner` does some computations related to prefix, suffix and infix which are redundant in case of plain concatenation

This is why new class `StringChain` is created mainly as a copy of `java.util.StringJoiner` without prefix, suffix and infix.
Instead `StringChain` can be constructed with array of predefined size for the cases when number of concatenated instances of
`CharSequence` is known. This allows to dodge array reallocation and data copying. 

Results of benchmarking using JDK 11 on my machine (Intel i7-7700) can be found in file [./results/StringBuilderVsStringChainBenchmark.txt](./results/StringBuilderVsStringChainBenchmark.txt)