public class todo extends Task{
    private char category;

    public todo(String desc){
        super(desc);
        this.category = 'T';
    }
    public char getCat(){
        return this.category;
    }
}
