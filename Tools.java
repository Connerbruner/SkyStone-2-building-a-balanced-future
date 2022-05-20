import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Tools {
    //the Colors
    public static final String SCREEN_CLEAR = "\033[H\033[2J";
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    Scanner scanner = new Scanner( System.in );
    
    public void CreateMatches ( String filePath , int[] arr ,int rounds) {
        File fileToBeModified = new File( filePath );
        FileWriter writer = null;
        try {
            ArrayList<Integer> teamQueue = new ArrayList <>(  );
            writer = new FileWriter( fileToBeModified );
    
            for(int r=rounds; r>0 || !teamQueue.isEmpty(); r--)
            {
    
                for ( int j : arr ) {
                    teamQueue.add( j );
                }
                while (teamQueue.size()>1)
                {
                    int team1= random( 0 , teamQueue.size( ) - 1 );
                    int team2= random( 0 , teamQueue.size( ) - 1 );
                    while(teamQueue.get(team1).equals(teamQueue.get(team2)))
                    {
                        team2 = random( 0 , teamQueue.size( ) - 1 );
                    }
                    writer.write( teamQueue.get( team1 )  +" , "+ teamQueue.get( team2 )+"\n");
                    if(team1>team2)
                    {
                    teamQueue.remove( team1 ); 
                    teamQueue.remove( team2 );
                    }
                    else
                    {
                    teamQueue.remove( team2 ); 
                    teamQueue.remove( team1 );
                    }

                }
            }
                
            
            
        } catch ( IOException e ) {
            e.printStackTrace( );
        } finally {
            try {
                //Closing the resources
                assert writer != null;
                writer.close( );
            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }
    }
    
    /**
     * @return array of saved vars
     */
    public String nexMatch ( String file ) {
        
        try {
            File txt = new File( file );
            FileReader fileRead = new FileReader( txt );
            BufferedReader reader = new BufferedReader( fileRead );
            
            
            String match   ="hello";
            ArrayList <String> matches = new ArrayList <>(  );
            while(match!=null)
            {
                match = reader.readLine( );
                if(match!=null)
                {
                    matches.add(match);
                }
            }
            reader.close( );
            FileWriter writer=null;
            try {
                writer = new FileWriter( file );
                for(int i=1; i<matches.size(); i++)
                {
                    writer.write( matches.get( i )+"\n" );
                }
                writer.close( );
                return matches.get( 0 );
            }
            catch ( IOException e ) {
                e.printStackTrace( );
            }
 
        } catch ( IOException e ) {
            e.printStackTrace( );
            return null;
        }
        return null;
        
    }
    

    
    /**
     * @return RANDOM NUMBER between low and high iirc LETS GO
     */
    public int random ( int low , int high ) {
        int range = high - low + 1;
        return ( int ) ( Math.random( ) * range ) + low;
    }

    

    public void timer ( int time ) {
        long startTime = System.currentTimeMillis( );
        while ( startTime + time > System.currentTimeMillis( ) )
        {

            long sec = ((startTime + time-System.currentTimeMillis( ))/1000)%60;
            int min =  (int)(((startTime + time-System.currentTimeMillis( ))/1000)/60);
            if(sec<10)
            {
                System.out.print("\r "+ min+" : 0"+sec );
            }
            else
            {
                System.out.print("\r "+ min+" : "+sec );
            }
            System.out.flush();
        }
    }

    
    /**
     * @return Is that string castable
     */
    public boolean strIsInt ( String string ) {
        try {
            int value = Integer.parseInt( string );
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }
    public boolean choice (String str ) {
        System.out.println( str );
        String skip = scanner.nextLine( );
        boolean yesSkip = skip.equals( "yes" ) || skip.equals( "Yes" ) || skip.equals( "Y" ) || skip.equals( "y" );
        return yesSkip;
    }

    public int input( String str )
    {
        System.out.println( str );
        return scanner.nextInt();
    }
    public void match()
    {
        String team = nexMatch( "Match.txt" );
        System.out.println( "WELCOME TO TODAY'S MATCH " );
        System.out.println( "OUR TEAM IS "+team );
        while(choice( "Are you ready to Start? " ));
        
        System.out.print( SCREEN_CLEAR );
        System.out.println( "Auto Starting in " );
        timer(10000);
        
        System.out.print( SCREEN_CLEAR );
        System.out.println( "Auto ending in " );
        timer(30000);
        
        System.out.print( SCREEN_CLEAR );
        System.out.println( "TeleOP Starting in " );
        timer(10000);
    
        System.out.print( SCREEN_CLEAR );
        System.out.println( "TeleOP Ending in " );
        timer(120000);
    
        System.out.print( SCREEN_CLEAR );
        System.out.println( "Game ending ending in " );
        timer(30000);
        System.out.print( SCREEN_CLEAR );
        System.out.println( "GAME COMPLETE" );
        int height = input( "How tall was was this teams tower? " );
        
        int stone1 = input( "How high is capstone #1 " );
        int stone2 = input( "How high is capstone #2 " );
        
        int sky1 = input( "How high is SkyStone #1 " );
        int sky2 = input( "How high is SkyStone #2 " );
        
        int stonesU = input( "How many items are on the hub but not connected " );
        int stones = input( "How many items are on the hub and connected " );
        
        int hub = input( "Is the hub under tape (0 or 1)" );
        
        int penalties = input( "Take off any points for penalties " );
        int total = height+(stone1*3)+(stone2*3)+(sky1*2)+(sky2*2)+(stonesU*2)+(stones*4)+(hub*7)-penalties;
        System.out.println("FINAL SCORE "+total);
    }

    
    

}