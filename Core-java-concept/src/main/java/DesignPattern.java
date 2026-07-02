public class DesignPattern {

    // The Complex Product
    static class CustomPizza {
        // Required field
        private final String crust;
        // Optional fields with defaults
        private final boolean cheese;
        private final boolean pepperoni;

        // 🔒 Private constructor: Only the Builder can call this!
        private CustomPizza(Builder builder) {
            this.crust = builder.crust;
            this.cheese = builder.cheese;
            this.pepperoni = builder.pepperoni;
        }

        @Override
        public String toString() {
            return "Pizza [Crust=" + crust + ", Cheese=" + cheese + ", Pepperoni=" + pepperoni + "]";
        }

        // 🏗️ The Static Nested Builder Class
        public static class Builder {
            private final String crust; // Required field stays here
            private boolean cheese = false;    // Default values
            private boolean pepperoni = false;

            // Constructor forces the user to provide required data
            public Builder(String crust) {
                this.crust = crust;
            }

            // Fluent setter for cheese (returns the builder itself)
            public Builder addCheese() {
                this.cheese = true;
                return this; // 🔗 Returns 'this' to allow method chaining
            }

            // Fluent setter for pepperoni
            public Builder addPepperoni() {
                this.pepperoni = true;
                return this;
            }

            // 🎯 The final assembly button
            public CustomPizza build() {
                return new CustomPizza(this); // Passes itself to the private constructor
            }
        }
    }

    public static void main(String[] args) {
        // 🏁 Fluent Step-by-Step Construction
        CustomPizza clientOrder = new CustomPizza.Builder("Thick-Crust") // Step 1: Base
                .addCheese()                     // Step 2: Option
                .addPepperoni()                  // Step 3: Option
                .build();                        // Step 4: Assemble

        System.out.println("Builder Output: " + clientOrder);
    }
}