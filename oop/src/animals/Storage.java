package animals;

import java.util.Arrays;
import java.util.List;

public class Storage  {
    public Cat[] cats;

    public Storage() {
        this.cats = new Cat[0];
    }

    public void add(Cat cat) {
            Cat[] ans = new Cat[this.cats.length+1];
            System.arraycopy(cats, 0, ans, 0, cats.length);
            ans[ans.length - 1] = cat;
            cats = ans;
    }

//    Try to convert array to arraylist and use methods of arraylist
//    public void add(Cat cat) {
//       List <Cat> newCat =  Arrays.asList(this.cats);
//       newCat.add(cat);
//       newCat.toArray(this.cats);
//    }


    public void remove(Cat cat) {
        for(int i=0; i < this.cats.length; i++){
            Cat[] newCats;
           if(this.cats[i].equals(cat)) {
               newCats = new Cat[this.cats.length-1];
               this.cats[i] = null;
               int counter = 0;
               for (int j = 0; j < this.cats.length; j++) {
                   if (this.cats[j] != null) {
                       newCats[counter] = this.cats[j];
                       counter++;
                   }
               }
               this.cats = newCats;
               break;
           }
        }
    }

//    Try to convert array to arraylist and use methods of arraylist
//    public void remove(Cat cat) {
//        List <Cat> newCat =  Arrays.asList(this.cats);
//        newCat.remove(cat);
//        newCat.toArray(this.cats);
//    }

    public void edit(Cat cat) {
        for(int i = 0; i < this.cats.length; i++){
            if(this.cats[i].equals(cat)) {
                this.cats[i]=cat;
            }
        }
    }

//    Try to convert array to arraylist and use methods of arraylist
//    public void edit(Cat cat) {
//        List <Cat> newCat =  Arrays.asList(this.cats);
//        for(int i=0; i < newCat.size(); i++)
//        {
//            if(newCat.get(i).equals(cat)) {
//                newCat.set(i,cat);
//            }
//        }
//    }

    public Cat[] getAll() {
        return cats;
    }
}
