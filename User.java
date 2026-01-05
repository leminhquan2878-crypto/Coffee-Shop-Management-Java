public class User {
    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected boolean loggedIn;

    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void login() {
        this.loggedIn = true;
        System.out.println("[" + name + "] da dang nhap.");
    }

    public void logout() {
        this.loggedIn = false;
        System.out.println("[" + name + "] da dang xuat.");
    }
}
