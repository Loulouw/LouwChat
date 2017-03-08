package fr.loulouw.louwchat;


import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.Collections;


public class Eg {

    public static final String ee1 = "d48e6d5f7b27564c9722dd6a256918c4";
    public static final String ee1t = "WW91J3ZlIGJlZW4gcmlja3JvbGxlZCAhDQo9PT09PT09PT09DQpOZXZlciBnb25uYSBsZXQgeW91IGRvd24NCk5ldmVyIGdvbm5hIHJ1biBhcm91bmQgYW5kIGRlc2VydCB5b3UNCk5ldmVyIGdvbm5hIG1ha2UgeW91IGNyeQ0KTmV2ZXIgZ29ubmEgc2F5IGdvb2RieWUNCk5ldmVyIGdvbm5hIHRlbGwgYSBsaWUgYW5kIGh1cnQgeW91DQpOZXZlciBnb25uYSBnaXZlIHlvdSB1cA0KTmV2ZXIgZ29ubmEgbGV0IHlvdSBkb3duDQpOZXZlciBnb25uYSBydW4gYXJvdW5kIGFuZCBkZXNlcnQgeW91DQpOZXZlciBnb25uYSBtYWtlIHlvdSBjcnkNCk5ldmVyIGdvbm5hIHNheSBnb29kYnllDQpOZXZlciBnb25uYSB0ZWxsIGEgbGllIGFuZCBodXJ0IHlvdQ0KTmV2ZXIgZ29ubmEgZ2l2ZSB5b3UgdXANCk5ldmVyIGdvbm5hIGxldCB5b3UgZG93bg0KTmV2ZXIgZ29ubmEgcnVuIGFyb3VuZCBhbmQgZGVzZXJ0IHlvdQ0KTmV2ZXIgZ29ubmEgbWFrZSB5b3UgY3J5DQpOZXZlciBnb25uYSBzYXkgZ29vZGJ5ZQ0KTmV2ZXIgZ29ubmEgdGVsbCBhIGxpZSBhbmQgaHVydCB5b3U=";
    private static ArrayList<String> ee1p;


    public static ArrayList<String> ee1f() {
        if (ee1p == null) {
            ee1p = new ArrayList<>();
            String d = new String(Base64.decodeBase64(ee1t));
            String tab[] = d.split("\\n");
            Collections.addAll(ee1p, tab);
        }
        return ee1p;
    }

}
