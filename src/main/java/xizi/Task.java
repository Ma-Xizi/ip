package xizi;

public abstract class Task {
    String name;
    boolean isDone;

    Task(String command){
        this.name = command;
        this.isDone = false;
    }

    void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public void markDone(){
        this.isDone = true;
    }

    public void unmark(){
        this.isDone = false;
    }
    @Override
    public String toString(){
        StringBuilder stringbuilder = new StringBuilder();
        if (this.isDone){
            stringbuilder.append("[X] ");
        }else{
            stringbuilder.append("[ ] ");
        }
        stringbuilder.append(this.name);

        return stringbuilder.toString();
    }

    public abstract String toFileFormat();




}
