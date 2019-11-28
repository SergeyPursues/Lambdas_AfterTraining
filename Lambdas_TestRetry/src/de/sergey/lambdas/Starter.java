package de.sergey.lambdas;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Starter {
public static Map<String, Person> customersMap = new HashMap<>();
	
	
	public static void main(String[] args) {
//		Map<String, Person> customersMap = new HashMap<>();
		customersMap.put("id_1", new Person("Tom", "Becks"));
		customersMap.put("id_2", new Person("Benny", "Blank"));
		customersMap.put("id_3", new Person("Thor", "Samuelson"));
		customersMap.put("id_4", new Person("Tiffany", "Banks"));
		customersMap.put("id_5", new Person("Johnny", "Singer"));
		
		Set<String> result = filterOut_id2AndReturnMapKeysAsSet((keks)-> {
			keks.remove("id_3");
			return keks.keySet();
		}, customersMap);
		
		System.out.println(result);
		
		Person pl = findByLastname("Banks");
		System.out.println(pl);
		
		Person pf = findByFirstname("Johnny");
		System.out.println(pf);
		
		maps();
	}
	
	public static Set<String> filterOut_id2AndReturnMapKeysAsSet(Function<Map<String, Person>, Set<String>> function, Map<String, Person> r){
		for (int i = 0; i < 2; i++) {
			try {
				if (i == 0) {
					throw new Exception();
				}
				System.out.println(i);
				return function.apply(r);
			} catch (Exception exc) {}
		}
		return null;
	}
	
	private static Person findByLastname(String lastname) {
		return handleQuery (person -> person.getLastname().equals(lastname));	
	}
	
	private static Person findByFirstname(String firstname) {
		return handleQuery (person -> person.getFirstname().equals(firstname));	
	}

	private static Person handleQuery(Predicate<Person> pred) {
		Collection<Person> customersList = customersMap.values();
		return customersList.stream()
							.filter(pred)
							.findAny()
//		 					.collect(Collectors.toList()).get(0)
							.orElse(null);
	}
	
	private static void maps() {
		Map<Integer, String> HOSTING = new HashMap<>();
        HOSTING.put(1, "linode.com");
        HOSTING.put(2, "heroku.com");
        HOSTING.put(3, "digitalocean.com");
        HOSTING.put(4, "aws.amazon.com");
        
        Set<Entry<Integer, String>> entrySet = HOSTING.entrySet();
        Set<Integer> keySet = HOSTING.keySet();
        Collection<String> values = HOSTING.values();
        
        String newLine = System.getProperty("line.separator");
        System.out.println("EntrySet:" + entrySet + newLine + "KeySet:" + keySet + newLine + "Values:" + values );
         
//        for (Entry<Integer, String> entry : entrySet) {
//			System.out.println(entry.getValue());
//		}
        
        //Map -> Stream -> Filter -> String
        String result = HOSTING.entrySet().stream()
                .filter(map -> "aws.amazon.com".equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());

        System.out.println("With Java 8 : " + result);

        // filter more values
        result = HOSTING.entrySet().stream()
                .filter(x -> {
                    if (!x.getValue().contains("amazon") && !x.getValue().contains("digital")) {
                        return true;
                    }
                    return false;
                })
                .map(map -> map.getValue())
                .collect(Collectors.joining(", "));

        System.out.println("With Java 8 : " + result);
        
        List<String>list = Arrays.asList("a", "b", "c");
        String[] array = (String[]) list.toArray();
        for (String string : array) {
        	System.out.println(string);
			
		}
     
    }

	
}
