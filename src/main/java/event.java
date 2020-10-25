public class event extends Task{
    private char category;
    String time;

    public event(String desc, String timing){
        super(desc);
        this.time = timing;
        this.category = 'E';
    }
    public String getTime(){
        return this.time;
    }
    public char getCat(){
        return this.category;
    }
}
