package me.pr3.JNode.Var;

import java.util.Arrays;
import java.util.HashMap;

public class GlobalPool {

    public GlobalPool(Var... vars) {
        Arrays.stream(vars).forEach(n -> this.vars.put(n.getName(), n));
    }

    public HashMap<String, Var> getVars() {
        return vars;
    }

    private HashMap<String, Var> vars = new HashMap<>();

}
