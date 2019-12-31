import java.util.ArrayList;

public class Repo {
    ArrayList<Integer> intRepo;
    private static Repo repo;
    private Repo(){
        intRepo = new ArrayList<Integer>();
    }

    public static Repo getRepo(){
        if (repo == null){
            repo = new Repo();
        }
        return repo;
    }

}
