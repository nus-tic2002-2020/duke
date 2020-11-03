package main;

public class Parser
{
//deals with making sense of the user command
    public void parseCommand(String input, UI ui, TaskList task, Storage storage)
    {

        try
        {
            String[] arrValue = input.split(" ");
            if (input.equals("list"))
            {
                ui.printTaskList(task.getCount(), task);
            }
            else if (arrValue[0].equals("done"))
            {
                if(arrValue.length < 2)
                {
                    throw new EmptyDescriptionException("Oops. The description of a done cannot be empty");
                }
                int index = Integer.parseInt(arrValue[1]);
                task.get(index-1).markAsDone();
                ui.printDone(task, index);
                storage.save("listData.txt", task);
            }

            else if (arrValue[0].equals("todo"))
            {
                if(arrValue.length < 2)
                {
                    throw new EmptyDescriptionException("Oops. The description of a todo cannot be empty");
                }
                String replaceString = input.replace("todo ", "");
                task.newTodoTask(replaceString, false);
                ui.printTodo(task, task.getCount()-1);
                storage.save("listData.txt", task);

            }
            else if (arrValue[0].equals("deadline"))
            {
                if(arrValue.length < 2)
                {
                    throw new EmptyDescriptionException("Oops. The description of a deadline cannot be empty");
                }
                String replaceString = input.replace("deadline ","");
                String [] splitBy = replaceString.split(" /by ");
                if(splitBy.length < 2)
                {
                    throw new EmptyDescriptionException("Oops. The date of a event cannot be empty");
                }
                task.newDeadlineTask(splitBy[0], false, splitBy[1]);
                ui.printDeadline(task, task.getCount()-1);
                storage.save("listData.txt", task);
            }

            else if (arrValue[0].equals("event"))
            {
                if(arrValue.length < 2)
                {
                    throw new EmptyDescriptionException("Oops. The description of a event cannot be empty");
                }
                String replaceString = input.replace("event", "");
                String [] splitAt = replaceString.split(" /at ");
                if(splitAt.length < 2)
                {
                    throw new EmptyDescriptionException("Oops. The date of a event cannot be empty");
                }
                task.newEventTask(splitAt[0], false, splitAt[1]);
                ui.printEvent(task, task.getCount()-1);
                storage.save("listData.txt", task);
            }

            else if (arrValue[0].equals("delete"))
            {
                if(arrValue.length < 2)
                {
                    throw new EmptyDescriptionException("Oops. The description of a delete cannot be empty");
                }
                int index = Integer.parseInt(arrValue[1]);
                Task t = task.removeTask(index);
                ui.printDelete(t, task.getCount()+1, index);
                storage.save("listData.txt", task);
            }

            else if(!arrValue[0].equals("bye")) {
                throw new InvalidCommandException("Whoops!!!");
            }

        }
        catch (InvalidCommandException e)
        {
            ui.printInvalidCommandMessage();
        }
        catch (EmptyDescriptionException e)
        {
            System.out.println(e.getMessage());
        }

    }


}
