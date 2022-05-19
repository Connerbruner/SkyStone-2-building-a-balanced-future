class Main {
  public static void main(String[] args) {
    Tools tool = new Tools();
    int[] teams = {8693,5050,4000,0001,0002};
    tool.CreateMatches( "Matches.txt",teams,5 );
    System.out.println( "Matches complete" );
  }
}