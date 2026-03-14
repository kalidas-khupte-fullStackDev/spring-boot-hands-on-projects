public class UserBuilderPattern {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserBuilderPattern{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private UserBuilderPattern(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class UserBuilder {
        private String name;
        private int age;

        public UserBuilder setName(String name){
            this.name = name;
            return this;
        }

        public UserBuilder setAge(int age){
            this.age = age;
            return this;
        }

        public UserBuilderPattern build(){
            return new UserBuilderPattern(this);
        }
    }
}