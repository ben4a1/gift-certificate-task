package ru.clevertec.ecl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepositoryHibernate;
import ru.clevertec.ecl.dal.entity.GiftCertificate;
import ru.clevertec.ecl.dal.entity.Tag;
import ru.clevertec.ecl.utils.HibernateUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.*;
import static java.time.LocalDateTime.*;

public class HibernateMain {
    private static List<GiftCertificate> gifts;
    private static List<Tag> tags;

    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.getSessionFactory()) {

            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
            session.beginTransaction();

            var certificateRepository = new GiftCertificateRepositoryHibernate(session);
            certificateRepository.findById(2L).ifPresent(System.out::println);

            session.getTransaction().commit();
        }
    }

    private static List<GiftCertificate> getCertificates() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(1L)
                .name("Certificate1")
                .price(new BigDecimal(15))
                .description("descccr")
                .createDate(now())
                .duration(ofDays(15))
                .build());
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(2L)
                .name("Certificate2")
                .price(new BigDecimal(25))
                .description("desasdcr")
                .createDate(now())
                .duration(ofDays(25))
                .build());
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(3L)
                .name("Certificate3")
                .price(new BigDecimal(1))
                .description("etwrw qwe")
                .createDate(now())
                .duration(ofDays(30))
                .build());
        certificates.add(GiftCertificate.aGiftCertificate()
                .id(4L)
                .name("Certificate4")
                .price(new BigDecimal(456))
                .description("vot eto da")
                .createDate(now())
                .duration(ofDays(45))
                .build());
        return certificates;
    }

    private static List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.aTag()
                .id(25L)
                .name("Tag1")
                .build());
        tags.add(Tag.aTag()
                .id(null)
                .name("Tag1234")
                .build());
        return tags;
    }
}
