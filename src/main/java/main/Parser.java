package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /***
     * to parse command into dukeapp
     * to parse commands into dukeapp
     * @param input
     * @param ui
     * @param task
     * @param storage
     */
    public void parseCommand(String input, UI ui, TaskList task, Storage storage) {
        try {
            String[] arrValue = input.split(" ");
            if (input.equals("list")) {
                ui.printTaskList(task.getCount(), task);
            } else if (arrValue[0].equals("done")) {
                if(arrValue.length < 2) {
                    throw new EmptyDescriptionException("Oops. The description of a done cannot be empty");
                }
                int index = Integer.parseInt(arrValue[1]);
                task.get(index-1).markAsDone();
                ui.printDone(task, index);
                storage.save("listData.txt", task);
            }
            else if (arrValue[0].equals("todo")) {
                if(arrValue.length < 2) {
                    throw new EmptyDescriptionException("Oops. The description of a todo cannot be empty");
                }
                String replaceString = input.replace("todo ", "");
                task.newTodoTask(replaceString, false);
                ui.printTodo(task, task.getCount()-1);
                storage.save("listData.txt", task);
            }
            else if (arrValue[0].equals("deadline")) {
                if (arrValue.length < 2) {
                    throw new EmptyDescriptionException("Oops. The description of a deadline cannot be empty");
                }
                String replaceString = input.replace("deadline ", "");
                String[] splitBy = replaceString.split(" /by ");
                //Don't allow user to input date earlier than today's today
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
                LocalDateTime deadlineDate = LocalDateTime.parse(splitBy[1], formatter1);
                if(deadlineDate.isBefore(LocalDateTime.now())) {
                    System.out.println("Deadline must not be before today's date!!");
                }
                else {
                    if (splitBy.length < 2) {
                        throw new EmptyDescriptionException("Oops. The date of a deadline cannot be empty");
                    }
                    String[] repeatChunk = splitBy[1].split("/repeat");
                    if (repeatChunk.length == 1) {
                        if (replaceString.contains("/repeat")) {
                            throw new EmptyDescriptionException("Oops. Please place the amount of days between each repeated event. e.g. /repeat 7");
                        }
                        task.newDeadlineTask(splitBy[0], false, splitBy[1]);
                        ui.printDeadline(task, task.getCount() - 1);
                        storage.save("listData.txt", task);
                    } else {
                        String[] timesChunk = repeatChunk[1].split("/times");
                        if (timesChunk.length == 1) {
                            throw new EmptyDescriptionException("Oops. Please place number of times event is to be repeated. e.g. /times 7");
                        }
                        String daysString = timesChunk[0].trim();
                        int days = Integer.parseInt(daysString); // get number of days
                        String timesString = timesChunk[1].trim();
                        int times = Integer.parseInt(timesString);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mma");
                        LocalDateTime date = LocalDateTime.parse(repeatChunk[0].trim(), formatter);
                        for (int i = 0; i < times; i++) {
                            task.newDeadlineTask(splitBy[0], false, date);
                            date = date.plusDays(days);
                            ui.printDeadline(task, task.getCount() - 1);
                        }
                        storage.save("listData.txt", task);
                    }
                }
            }
            else if (arrValue[0].equals("event")) {
                if(arrValue.length < 2) {
                    throw new EmptyDescriptionException("Oops. The description of a event cannot be empty");
                }

                String replaceString = input.replace("event ", "");
                String [] splitAt = replaceString.split(" /at ");
                //Don't allow user to input date earlier than today's today
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
                LocalDateTime eventDate = LocalDateTime.parse(splitAt[1], formatter1);
                if(eventDate.isBefore(LocalDateTime.now())) {
                    System.out.println("Event must not be before today's date!!");
                }
                else {
                    if (splitAt.length < 2) {
                        throw new EmptyDescriptionException("Oops. The date of a event cannot be empty");
                    }
                    String[] repeatChunk = splitAt[1].split("/repeat");
                    if (repeatChunk.length == 1) {
                        if (replaceString.contains("/repeat")) {
                            throw new EmptyDescriptionException("Oops. Please place the amount of days between each repeated event. e.g. /repeat 7");
                        }
                        task.newEventTask(splitAt[0], false, splitAt[1]);
                        ui.printEvent(task, task.getCount() - 1);
                        storage.save("listData.txt", task);
                    } else {
                        String[] timesChunk = repeatChunk[1].split("/times");
                        if (timesChunk.length == 1) {
                            throw new EmptyDescriptionException("Oops. Please place number of times event is to be repeated. e.g. /times 7");
                        }
                        String daysString = timesChunk[0].trim();
                        int days = Integer.parseInt(daysString); // get number of days
                        String timesString = timesChunk[1].trim();
                        int times = Integer.parseInt(timesString);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d hh:mma");
                        LocalDateTime date = LocalDateTime.parse(repeatChunk[0].trim(), formatter);
                        for (int i = 0; i < times; i++) {
                            task.newEventTask(splitAt[0], false, date);
                            date = date.plusDays(days);
                            ui.printEvent(task, task.getCount() - 1);
                        }
                        storage.save("listData.txt", task);
                    }
                }
            }
            else if (arrValue[0].equals("delete")) {
                if(arrValue.length < 2) {
                    throw new EmptyDescriptionException("Oops. The description of a delete cannot be empty");
                }
                int index = Integer.parseInt(arrValue[1]);
                Task t = task.removeTask(index);
                ui.printDelete(t, task.getCount()+1, index);
                storage.save("listData.txt", task);
            }
            else if (arrValue[0].equals("find")) {
                TaskList results = task.find(input.replace("find", "").trim());
                ui.printTaskList(results.getCount(), results);
            }
            else if(!arrValue[0].equals("bye")) {
                throw new InvalidCommandException("Invalid command");
            }
        }
        catch (InvalidCommandException e) {
            ui.printInvalidCommandMessage();
        }
        catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
        catch(DateTimeParseException e) {
            ui.printDateTimeExceptionMessage();
        }
    }
}