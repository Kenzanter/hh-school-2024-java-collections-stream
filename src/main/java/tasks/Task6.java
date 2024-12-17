package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Map<Integer, Area> areaMap = areas.stream()
        .collect(Collectors.toMap(Area::getId, area -> area));

    return persons.stream()
        .flatMap(person -> personAreaIds.get(person.id()).stream()
            .map(regionId -> getPersonDiscription(person, areaMap.get(regionId)))
        )
        .collect(Collectors.toSet());
  }

  private static String getPersonDiscription(Person person, Area area){
    return String.format("%s - %s", person.firstName(), area.getName());
  }
}
