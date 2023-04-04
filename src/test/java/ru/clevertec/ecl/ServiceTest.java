package ru.clevertec.ecl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepository;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.service.mapper.hibernate.GiftCertificateCreateMapper;
import ru.clevertec.ecl.service.mapper.hibernate.GiftCertificateReadMapper;
import ru.clevertec.ecl.service.service.GiftCertificateService;
import ru.clevertec.ecl.utils.HibernateUtil;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.time.Duration;

public class ServiceTest {

    @Test
    void checkH2() {
        try (var sessionFactory = HibernateUtil.getSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
            var giftCertificateRepository = new GiftCertificateRepository(session);
            var giftCertificateReadMapper = new GiftCertificateReadMapper();
            var giftCertificateCreateMapper = new GiftCertificateCreateMapper();
            GiftCertificateCreateDto giftCertificateCreateDto = new GiftCertificateCreateDto(
                    "qwe",
                    "gift certificate vai-vai-vai",
                    new BigDecimal(100500),
                    Duration.ofDays(15));
            var giftCertificateService = new GiftCertificateService(giftCertificateRepository, giftCertificateReadMapper, giftCertificateCreateMapper);
            Long aLong = giftCertificateService.create(giftCertificateCreateDto);
        }
    }
}
