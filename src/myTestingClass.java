public class myTestingClass {
    private String key;

    public myTestingClass(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        myTestingClass other = (myTestingClass) obj;
        return key.equals(other.key);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + key.charAt(i);
        }
        return hash;
    }

    @Override
    public String toString() {
        return key;
    }
}