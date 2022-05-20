class Main {
  public static void main(String[] args) {
    Tools tool = new Tools();
    int[] teams = {8693,1111,2324,7777,5555};
    System.out.println("PASSWORD PLEASE: ");
    while(!tool.scanner.nextLine().equals(System.getenv("password")));
    System.out.print(tool.SCREEN_CLEAR);
    System.out.println("Welcome");
    while(true)
    {
    System.out.print("(N)ew matches, (P)lay match ");
    String str = tool.scanner.nextLine().toUpperCase();
    
    if(str.equals("N"))
    {
tool.CreateMatches("Matches.txt",teams,tool.input("How many matches should each team play? "));
    }
    if(str.equals("P"))
    {
        tool.match();
    }
    }
  }
}