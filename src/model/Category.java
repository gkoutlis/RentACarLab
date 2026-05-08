package model;

public class Category extends BaseEntity {
        private String kind;

        public Category(String kind){
            super();
            
            this.kind = kind;
        }

        public Category(int id, String kind){
            super(id);
            this.kind = kind;
        }

        public String getKind(){
        return kind;
    }

        public void  setKind(String kind){
            this.kind = kind;
        }

    @Override
        public String toString(){
            return kind;
        }

}
