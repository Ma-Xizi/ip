package xizi.chatbot.task;


/**
 * Represents a general task with a name and a completion status.
 * This is an abstract class that provides basic functionality for tasks.
 * Specific types of tasks should extend this class and implement the {@link #toFileFormat()} method.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified name and initializes it as not done.
     *
     * @param command The name or description of the task.
     */
    Task(String command) {
        this.name = command;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone {@code true} if the task is done, {@code false} otherwise.
     */
    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting its status to not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        if (this.isDone) {
            stringbuilder.append("[X] ");
        } else {
            stringbuilder.append("[ ] ");
        }
        stringbuilder.append(this.name);

        return stringbuilder.toString();
    }

    /**
     * Returns the string representation of the task in the format used for data file storage.
     * This method must be implemented by subclasses.
     *
     * @return A string in the file format to save the task.
     */
    public abstract String toFileFormat();




}
