package tr.com.kobis.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tr.com.kobis.R;

public class LeaseFragment extends Fragment
{

    public LeaseFragment()
    {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_lease, container, false);
        TextView kobis_kart = (TextView) rootView.findViewById(R.id.kobis_kart_ozellikleri);
        TextView kent_kart = (TextView) rootView.findViewById(R.id.kent_kart_ozellikleri);
        TextView kredi_kart = (TextView) rootView.findViewById(R.id.kredi_karti_ozellikleri);

        kobis_kart.setText("•\tKocaeli Fuarı içindeki Seyahat Kartları Bürosundan sisteme üye olarak 5 TL " +
                "karşılığında  KOBİS " +
                "üye kartlarınızı alabilir ve kontör yükleyerek bisiklet kiralayabilirsiniz. Üyelik için kimlik " +
                "bilgileri gerekmektedir. (Nüfus cüzdanı)\n\n" +

                "•\tKobis Üye Kartı alabilmek için 18 yaşını doldurmuş olmak gerekmektedir.(Yasa Gereği)\n" +
                "\n" +
                "•\tÜye kartınızı bisiklet park yerlerindeki panele okutup 4 haneli şifrenizi girerek giriş tuşuna " +
                "basarak bisikletinizi alabilirsiniz.\n\n" +
                "•\tBisikleti teslim ederken boş ve hizmet dışı olmayan park yerine bisikleti bırakarak ekranda " +
                "teslim alınmıştır uyarısını görmeniz gerekmektedir. Aksi takdirde sistemde bisikleti teslim " +
                "etmediğiniz görülür. \n\n" +
                "•\tKayıtlı üye değil iseniz üye işlemleri sayfasından üye ol linkine tıklayarak üyelik formunda " +
                "istenen bilgileri doldurarak anında sisteme üye olabilir. \n\n" +
                "•\tKart ücreti ve dolum işlemlerini kredi kartınız ile web sitesindeki Üye Kartı Al-Kredi Yükle " +
                "linkinden yapabilirsiniz. \n\n" +
                "•\tKredi kartı kullanmadan  da üye kartınızı Fuar içi Toplu Taşıma Dairesi Başkanlığı Seyahat " +
                "Kartları Biriminden sisteme internet üzerinden üye olduğunuzu belirterek 5 TL karşılığında alabilir " +
                "ve dolum yapabilirsiniz.");

        kent_kart.setText("•\tKent içi toplu taşımada kullanılan Kent kartlarına kişinin T.C. Kimlik Numarası ve " +
                "Adres " +
                "bilgilerini entegre ederek bisiklet kiralama işlemi gerçekleştirilir. \n\n" +
                "•\t Bu işlem sadece Kocaeli Fuarı içindeki Seyahat Kartları Bürosundan yapılmaktadır.\n\n" +
                "•\tKent Kartı ile bisiklet kiralayabilmek için 18 yaşını doldurmuş olmak gerekmektedir.(Yasa Gereği)" +
                " \n\n" +
                "•\tKent kartınızla kiosktaki adımları takip ederek bisiklet kiralayabilirsiniz. \n\n" +
                "•\tBisiklet park yerlerindeki panele 4 haneli şifrenizi girerek giriş tuşuna basarak bisikletinizi " +
                "alabilirsiniz. \n\n" +
                "•\tBisikleti teslim ederken boş ve hizmet dışı olmayan park yerine bisikleti bırakarak ekranda " +
                "teslim alınmıştır uyarısını görmeniz gerekmektedir. Aksi takdirde sistemde  bisikleti teslim " +
                "etmediğiniz  görülür. \n\n" +
                "•\tBisikleti teslim ettikten sonra tekrar kiosk'a gelip kiralama sırasında yapılmış olan blokeyi " +
                "hesap işlemlerinde çözmeniz gerekmektedir. Aksi takdirde kartınızdaki bloke kalkmamış olacaktır. " +
                "\n\n" +
                "•\tŞuan an 18 yaşını doldurmuş öğrenci ve öğretmen kent kartları ile kiralama yapılabilmektedir.\n\n" +
                "•\tİsimsiz kent kartlar ile kiralama işlemi test aşamasında olup en kısa zamanda kullanıma " +
                "açılacaktır.");
        kredi_kart.setText("•\tKredi kartı ile kiralama işlemi test aşamasında olup en kısa zamanda kullanıma açılacaktır." );

        return rootView;
    }


}
