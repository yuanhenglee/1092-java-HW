import java.util.*;


public class FindTheRelationship {

	public static class Person {
		public Boolean friendOfSomeone;
		public String name ;
		public int age ;
		public Set<String> friends;

		public Person( String name, int age, String friends ){
			this.friendOfSomeone = false;
			this.name = name;
			this.age = age;
			String[] tokens = friends.split(",");
			this.friends = new HashSet<String>();
			for( String token : tokens ){
				this.friends.add(token);
			}
		}

		public String toString(){
			return this.name + " " + this.age + " " + this.friends;
		}

	}

	public static class SortByName implements Comparator<Person>{
		public int compare( Person a, Person b ){
			return a.name.compareTo(b.name);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Person> people = new ArrayList<Person>();
		while( scanner.hasNext() ){
			String name = scanner.next();
			int age = scanner.nextInt();
			String friends = scanner.next();
			Person p = new Person(name, age, friends);
			people.add( p );
		}

		System.out.println("107703004");

		// task1
		System.out.println("--task1--");
		int sum = 0;
		for( Person p : people ){ sum+=p.age; }
		double mean = Double.valueOf(sum)/people.size();
		System.out.println(Math.round(mean));

		// task2
		System.out.println("--task2--");
		List<Person> ps = new ArrayList<Person>();
		for( Person p : people )
			if( p.name.contains("Beneviento") && p.friends.contains("Chris.Redfield") )
				ps.add( p );
		Collections.sort(ps, new SortByName());
		for( Person p : ps )
			System.out.println(p.name);

		// bonus 
		System.out.println("--bonus--");
		for( Person p : people )
			for( String f : p.friends )
				for( Person p2 : people )
					if( p2.name.compareTo(f) == 0 )
						p2.friendOfSomeone = true;
		for( Person p : people )
			if( !p.friendOfSomeone )
				System.out.println(p.name);
	}

}
