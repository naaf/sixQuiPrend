package naaf.sixClient;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Appren
{
  public static void main(String[] args)
  {
    IntStream.range(0, 6).forEach(value -> System.out.println(value));
    List<Integer> list = IntStream.range(1, 6).boxed().collect(Collectors.toList());
    System.out.println(list);
    System.out.println("countList " + list.stream().count());
    System.out.println("Moy " + list.stream().collect(Collectors.averagingInt(item -> item)));
    System.out.println("sum " + list.stream().collect(Collectors.summarizingInt(value -> value)) );
  }
}
