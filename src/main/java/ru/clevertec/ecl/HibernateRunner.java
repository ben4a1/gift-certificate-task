package ru.clevertec.ecl;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.ecl.dal.dao.impl.GiftCertificateRepository;
import ru.clevertec.ecl.service.dto.GiftCertificateCreateDto;
import ru.clevertec.ecl.service.interceptor.TransactionInterceptor;
import ru.clevertec.ecl.service.mapper.hibernate.GiftCertificateCreateMapper;
import ru.clevertec.ecl.service.mapper.hibernate.GiftCertificateReadMapper;
import ru.clevertec.ecl.service.service.GiftCertificateService;
import ru.clevertec.ecl.utils.HibernateUtil;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.time.Duration;

public class HibernateRunner {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try (var sessionFactory = HibernateUtil.getSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            var transactionInterceptor = new TransactionInterceptor(sessionFactory);
            var certificateRepository = new GiftCertificateRepository(session);
            var giftCertificateReadMapper = new GiftCertificateReadMapper();
            var giftCertificateCreateMapper = new GiftCertificateCreateMapper();


            GiftCertificateService giftCertificateService = new ByteBuddy()
                    .subclass(GiftCertificateService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(GiftCertificateService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(GiftCertificateRepository.class, GiftCertificateReadMapper.class, GiftCertificateCreateMapper.class)
                    .newInstance(certificateRepository, giftCertificateReadMapper, giftCertificateCreateMapper);


            giftCertificateService.findById(3L).ifPresent(System.out::println);
            GiftCertificateCreateDto giftCertificateCreateDto = new GiftCertificateCreateDto(
                    "qwe",
                    "gift certificate vai-vai-vai",
                    new BigDecimal(100500),
                    Duration.ofDays(15));
            Long aLong = giftCertificateService.create(giftCertificateCreateDto);
            giftCertificateService.findAll().forEach(System.out::println);
        }
    }
}
