import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class Tools {
    //the Colors
    public static final String GO_BACK = "\"\\u001B[A\"";
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
    
            for(int r=0; r<rounds && teamQueue.isEmpty(); r++)
            {
    
                for ( int j : arr ) {
                    teamQueue.add( j );
                }
                while (teamQueue.size()>1)
                {
                    int team1= random( 0 , teamQueue.size( ) - 1 );
                    int team2= random( 0 , teamQueue.size( ) - 1 );
                    while( ! Objects.equals( teamQueue.get( team1 ) , teamQueue.get( team2 ) ) )
                    {
                        team2 = random( 0 , teamQueue.size( ) - 1 );
                    }
                    writer.write( teamQueue.get( team1 )  +" , "+ teamQueue.get( team1 ));
                    teamQueue.remove( team1 );
                    teamQueue.remove( team2 );
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

            reader.close( );
            return reader.readLine();
        } catch ( IOException e ) {
            e.printStackTrace( );
            return null;
        }
        
        
    }
    

    
    /**
     * @return RANDOM NUMBER between low and high iirc LETS GO
     */
    public int random ( int low , int high ) {
        int range = high - low + 1;
        return ( int ) ( Math.random( ) * range ) + low;
    }

    
    /**
     *
     */
    public void wait ( int time ) {
        long startTime = System.currentTimeMillis( );
        while ( startTime + time > System.currentTimeMillis( ) ) ;
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
        return ! yesSkip;
    }

    
    

}