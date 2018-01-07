package it.rezervare.beans.helper;

import java.util.Comparator;

import it.rezervare.beans.model.hibernateBeans.Zbor;

public class ZborComparator  implements Comparator<Zbor>{

	@Override
    public int compare(Zbor primulZbor,Zbor alDoileaZbor) {
        return primulZbor.getPret().compareTo(alDoileaZbor.getPret());
    }
	
}
