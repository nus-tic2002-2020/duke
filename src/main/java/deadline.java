public class deadline extends Task{
    private char category;
    String time;

    public deadline(String desc, String timing){
        super(desc);
        this.time = timing;
        this.category = 'D';
    }
    public String getTime(){
        return this.time;
    }
    public char getCat(){
        return this.category;
    }
}
