package tr.com.kobis.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tr.com.kobis.R;

public class PriceFragment extends Fragment
{
    public PriceFragment()
    {

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_price, container, false);
        TextView priceText = (TextView) rootView.findViewById(R.id.priceText);
        priceText.setText("Kullanım ücreti bisiklet park yerinden alındıktan sonra başlayıp tekrar her hangi bir " +
                "istasyondaki park yerine başarılı bir şekilde bırakılıncaya kadar geçen süreyi kapsamaktadır. " +
                "Ücretlendirme saat bazında yapılmaktadır. Günde bir defa  ilk kiralamada 30 dakika ücretsiz kullanım" +
                " hakkı verilmekte ve bu 30 dakikayı geçen her kiralama ücretlendirilmektedir." +
                "\n\n" +
                "Ücretlendirme Tablosu\n\n" +
                "•\tGünde 1 defa ilk kiralamada ilk 30 dakika ücretsiz kullanım,\n\n" +
                "•\t31-60 Dk 1 TL \n\n" +
                "•\t1-2 Saat Arası 2 TL\n\n" +
                "•\t2-3 Saat Arası 3 TL\n\n" +
                "•\t3-4 Saat Arası 4 TL\n\n" +
                "•\t4-5 Saat Arası 5 TL\n\n" +
                "* 1 saat ve üzeri kullanımda her saat için 1 TL ücret alınır.\n" +
                "\n");
        return rootView;
    }


}
