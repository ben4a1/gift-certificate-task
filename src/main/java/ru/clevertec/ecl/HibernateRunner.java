package ru.clevertec.ecl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepository;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.service.mapper.GiftCertificateCreateMapper;
import ru.clevertec.ecl.service.mapper.GiftCertificateReadMapper;
import ru.clevertec.ecl.service.service.GiftCertificateService;
import ru.clevertec.ecl.utils.HibernateUtil;

import javax.transaction.Transactional;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.time.Duration;

public class HibernateRunner {
    @Transactional
    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.getSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
            session.beginTransaction();

            var certificateRepository = new GiftCertificateRepository(session);
            var giftCertificateReadMapper = new GiftCertificateReadMapper();
            var giftCertificateCreateMapper = new GiftCertificateCreateMapper();
            var certificateService = new GiftCertificateService(
                    certificateRepository,
                    giftCertificateReadMapper,
                    giftCertificateCreateMapper);

            certificateService.findById(3L).ifPresent(System.out::println);
            GiftCertificateCreateDto giftCertificateCreateDto = new GiftCertificateCreateDto(
                    "gift",
                    "gift certificate",
                    new BigDecimal(100500),
                    Duration.ofDays(15));
            Long aLong = certificateService.create(giftCertificateCreateDto);

            session.getTransaction().commit();
        }
    }
}
