import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Font;



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
        FileWriter writer2 = null;
         try {
            writer2 = new FileWriter( "data.txt" );
            for(int i=0; i < arr.length; i++)
                 {
                     writer2.write(arr[i]+"\n");
                 }
             writer2.close( );
            }
        catch ( IOException e ) {
            e.printStackTrace( );
        }
        }
        

    
    /**
     * @return the next match happening
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
                try {
                    return matches.get( 0 );    
                }
                catch(IndexOutOfBoundsException e)
                {
                    System.out.println("No matches left");
                    return "";
                }
                
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

    

    public void timer ( int time , String text) {
        long startTime = System.currentTimeMillis( );
		JFrame frame = new JFrame("Time left");
        JLabel timer = new JLabel("A label");
        timer.setFont(new Font("Serif", Font.PLAIN, 50));

        frame.add(timer);
		frame.setSize(640,480);
		frame.setVisible(true);
        
        while ( startTime + time > System.currentTimeMillis( ) )
        {

            long sec = ((startTime + time-System.currentTimeMillis( ))/1000)%60;
            int min =  (int)(((startTime + time-System.currentTimeMillis( ))/1000)/60);
            if(sec<10)
            {
                timer.setText("<html>"+text+" <BR> "+ min+" : 0"+sec+"<html>" );
            }
            else
            {
               timer.setText("<html>"+text+" <BR> "+ min+" : "+sec+"<html>");
            }
            
        }
        frame.setVisible(false);
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
        return skip.equals( "yes" ) || skip.equals( "Yes" ) || skip.equals( "Y" ) || skip.equals( "y" );
    }

    public int input( String str )
    {
        System.out.println( str );
        return scanner.nextInt();
    }
    public void match()
    {
        String team = nexMatch( "Matches.txt" );
        System.out.println( "WELCOME TO TODAY'S MATCH " );
        System.out.println( "OUR TEAM IS "+team );
        while(!choice( "Are you ready to Start? " ));
        
        System.out.print( SCREEN_CLEAR );
        timer(10000,"Auto Starting in");
        timer(30000,"Auto ending in" );

        System.out.println("Open line.txt and press a key when someone goes over the team-specific line");
        
        timer(10000,"TeleOP Starting in ");
        timer(120000,"TeleOP Ending in ");
    
        timer(30000, "Game ending in ");
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
        try {
            File txt = new File( "line.txt" );
            FileReader fileRead = new FileReader( txt );
            BufferedReader reader = new BufferedReader( fileRead ); 
        int total = height+(stone1*3)+(stone2*3)+(sky1*2)+(sky2*2)+(stonesU*2)+(stones*4)+(hub*7)-penalties + (reader.readLine().length());
        
        System.out.println("FINAL SCORE "+total);
        int i;
        int j;
        for(i=1; strIsInt(team.substring(0,i)); i++);
        updateBoard(team.substring(0,i-1),team.substring(i+2,team.length()),total);
        }    

         catch ( IOException e ) {
                e.printStackTrace( );
            }


        
    }
    public void updateBoard(String team1, String team2,int score)
    {

        try {
            File txt = new File( "data.txt" );
            FileReader fileRead = new FileReader( txt );
            BufferedReader reader = new BufferedReader( fileRead );
            
            String team = "hello";
            ArrayList <String> teams = new ArrayList <>(  );
            while(team!=null)
            {
                team = reader.readLine( );
                if(team !=null)
                {
                    teams.add(team);
                }
            }
            reader.close();
            FileWriter writer = new FileWriter( txt );
            for(int i=0; i<teams.size(); i++)
            {
                String str = teams.get(i);   
                if(str.contains(team1))
                    {
                        if(team1.length() < str.length())
                        {
                            int cur = Integer.parseInt(str.substring(team1.length()+1));
                             writer.write(team1+" "+(score+cur)+"\n");
                        }
                        else
                        {
                            writer.write(team1+" "+score+"\n");
                        }
                    }
                    else if(str.contains(team2))
                    {
                        if(team2.length()+1<str.length())
                        {
                            int cur = Integer.parseInt(str.substring(team2.length()+1));
                             writer.write(team2+" "+(score+cur)+"\n");
                        }
                        else
                        {
                            writer.write(team2+" "+score+"\n");
                        }
                    }
                    else
                    {
                        writer.write(str+"\n");
                    }
            }
            writer.close();
            }
            catch ( IOException e ) {
                e.printStackTrace( );
            }
    }
    

    
    

}