import CarRentals.UI.Ui;


public class Main {



    public static void main(String[] args) {



        try {
            Ui ui = new Ui();
            ui.startUi();
        }
        catch (Exception e)
        {
            System.out.println("You're being very naughty right now!");
            System.out.println(e.getMessage());
        }


    }
}

