package com.example.summerShop.util.runner;

import com.example.summerShop.dto.*;
import com.example.summerShop.model.*;
import com.example.summerShop.model.enums.Gender;
import com.example.summerShop.repository.*;
import com.example.summerShop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RunnerApplicationPrestart {

    private final ProductsService productsService;
    private final ProductsRepository productsRepository;
    private final BrandsRepository brandsRepository;
    private final CategoriesRepository categoriesRepository;
    private final StoragesRepository storagesRepository;
    private final SizesRepository sizesRepository;

    @Autowired
    public RunnerApplicationPrestart(ProductsService productsService, ProductsRepository productsRepository,
                                     BrandsRepository brandsRepository, CategoriesRepository categoriesRepository,
                                     StoragesRepository storagesRepository, SizesRepository sizesRepository) {
        this.productsService = productsService;
        this.productsRepository = productsRepository;
        this.brandsRepository = brandsRepository;
        this.categoriesRepository = categoriesRepository;
        this.storagesRepository = storagesRepository;
        this.sizesRepository = sizesRepository;
    }

    public void run() {
        runTest();
        //runMain();
    }

    private void runTest() {
        for (int i = 0; i < 1000; i++) {
            productsService.findAllBrands();
        }
    }

    private void runMain() {
        try {
            createDirectories();
            initDatabase();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createDirectories() throws IOException {
        Path path = Paths.get("files");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    private void initDatabase() {
        productsRepository.deleteAll(productsRepository.findAll());
        brandsRepository.deleteAll(brandsRepository.findAll());
        categoriesRepository.deleteAll(categoriesRepository.findAll());

        create(new ProductReadDto(
                "Футболка жіноча",
                "Точний розмір допоможемо підібрати, за Вашим зростанням та вагою\n" + "Матеріал: бавовна, натуральні волокна\n" + "Зображення: фабричний друк, не тьмяніє в процесі прання\n" + "Класичний прямий крій, приємна тканина до тіла.\n",
                990.,
                new BrandGetDto(-1, "NIKE"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "1.webp");
        create(new ProductReadDto(
                "Шорти чоловічі",
                "Шорти вільні, легенькі, практично не відчуваються на тілі\n" +
                        "Бокові кармани без замків  Шнурівка на поясі М’яка тканина, приємна до тіла\n" +
                        " Призначення: для тренування в спортивному залі, бігу, заняття фітнесом\n" +
                        "Без підкладки в середині\n",
                1000.,
                new BrandGetDto(-1, "Highway"),
                new CategoryGetDto(-1, "Шорти"),
                "MAN",
                null,
                null
        ), "2.webp");
        create(new ProductReadDto(
                        "Купальник дівчачий",
                        "Дівчачий купальник з завищеною талією з яскравим принтом. Дуже приємний матеріал",
                        500.,
                        new BrandGetDto(-1, "Highway"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null
                ), "3.webp");
        create(new ProductReadDto(
                        "Купальник жіночий бікіні",
                        "Жіночий сексуальний купальник бікіні, чорний купальник із кільцями - це витончений та стильний варіант для пляжного відпочинку. Його чорний колір надає йому загадковості та елегантності. Купальник може мати класичний крій або бути в стилі бікіні з трикутним верхом і трусиками.",
                        1500.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null
                ), "4.webp");
        create(new ProductReadDto(
                        "Футболка с логотипом",
                        "Біла футболка символізує чистоту та простоту. Цей нейтральний колір ідеально підходить для будь-якого образу та стилю.",
                        2500.,
                        new BrandGetDto(-1, "Palm angels"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "5.webp");
        create(new ProductReadDto(
                        "Футболка с логотипом",
                        "Чорний колір футболки додає образу загадковості та елегантності. Він створює враження впевненості та стилю, роблячи вас центром уваги.",
                        2500.,
                        new BrandGetDto(-1, "Palm angels"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "6.webp");
        create(new ProductReadDto(
                        "Футболка с логотипом",
                        "Чорна футболка з місяцем - це ефектний та містичний образ, який вражає своєю загадковістю та таємничістю - це не лише одяг, але й вираз вашої індивідуальності та захоплення нічним небом. Вона створює враження таємничості і привертає увагу, роблячи вас центром уваги на будь-якому заході чи події.",
                        1500.,
                        new BrandGetDto(-1, "Balmain"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "7.webp");
        create(new ProductReadDto(
                        "Шорти чоловічі",
                        "Шорти з шовку з графічним принтом від Gucci. Біло-чорний шовковий графічний принт по всій поверхні. Застібка на ґудзиках спереду та дві прорізні кишені з боків.",
                        8500.,
                        new BrandGetDto(-1, "GUCCI"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "8.webp");
        create(new ProductReadDto(
                        "Футболка 'Пальмові Ангели'",
                        "Футболка 'Пальмові Ангели' з пальмовим принтом - це стильний та літній варіант одягу, який надає образу свіжості та екзотики. Ця футболка виготовлена з легкого та приємного на дотик матеріалу, ідеального для теплих днів або відпустки на екзотичних курортах. Вона має стрункий крій та виглядає неймовірно модно. Пальмовий принт на футболці додає їй свіжості та природної краси.",
                        2500.,
                        new BrandGetDto(-1, "Palm angels"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "9.webp");
        create(new ProductReadDto(
                        "Футболка з логотипом 'Versage'",
                        "Шовкова футболка Версаче з логотипом - це вишуканий та розкішний предмет гардеробу, який поєднує в собі елегантність та стиль із вишуканістю і комфортом. Логотип Версаче може бути вишитий або надрукований на футболка, створюючи вишуканий і вразливий ефект. Футболка виготовлена з високоякісного шовку, що надає їй неймовірну м'якість і ніжність на дотик.",
                        6500.,
                        new BrandGetDto(-1, "Versage"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "10.webp");
        create(new ProductReadDto(
                        "Шорти-бермуди Dolce & Gabbana",
                        "Шорти-бермуди Dolce & Gabbana з узором - це стильний та модний вибір для літнього сезону, який відзначається якістю, елегантністю та вишуканістю. Ці шорти-бермуди мають стильний крій, який завершується на рівні нижній частині коліна. Такий крій надає їм класичної елегантності та вишуканості, що робить їх відмінним вибором для різних подій та випадків.",
                        4500.,
                        new BrandGetDto(-1, "Dolce & Gabbana"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "11.webp");
        create(new ProductReadDto(
                        "Шорти-бермуди Dolce & Gabbana",
                        "Ці шорти-бермуди мають витончений крій, який завершується трохи нижче коліна. Вони виготовлені з високоякісного шовку, який надає їм неймовірну м'якість і гладкість. Шовкова тканина також надає шортам легкості та елегантності, зроблюючи їх відмінним вибором для літнього періоду. Шорти можуть мати еластичний пояс для кращої посадки та комфорту, а також кишені з боків, що додає їм функціональності.",
                        7500.,
                        new BrandGetDto(-1, "Dolce & Gabbana"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "12.webp");
        create(new ProductReadDto(
                        "Жіночий злитий купальник",
                        "Ця модель купальника має U-подібний виріз на спині. Особливо рекомендується жінкам більшої статури, оскільки така форма дозволяє легко одягати і знімати його через стегна і є дуже зручною.",
                        900.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "13.webp");
        create(new ProductReadDto(
                        "Жіночий злитий купальник",
                        "Ця модель купальника має U-подібний виріз на спині. Особливо рекомендується жінкам більшої статури, оскільки така форма дозволяє легко одягати і знімати його через стегна і є дуже зручною.",
                        1500.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "14.webp");
        create(new ProductReadDto(
                        "Жіночий роздільний купальник",
                        "Верхній частині купальника має спортивний крій з тонкими бретелями, які можна регулювати за потреби. Це дозволяє вам налаштовувати пасування для максимального комфорту та підтримки грудей під час активних рухів. Трусики цього купальника мають середню посадку і хороше покриття. Вони надійно фіксуються на стегнах, надаючи вам відчуття впевненості та комфорту під час рухів.",
                        1200.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "15.webp");
        create(new ProductReadDto(
                        "Жіночий суцільний купальник",
                        "Це стильний та модний вибір для активних жінок, які цінують комфорт і стиль під час пляжних забав та водних видів спорту. Цей купальник Nike з логотипом - це не лише функціональний одяг для пляжу, але і модний вираз стилю та елегантності. Він ідеально підходить для активних жінок, які хочуть виглядати стильно та відчувати себе комфортно під час пляжних відпочинків.",
                        1200.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "16.webp");
        create(new ProductReadDto(
                        "Жіночий злитий купальник",
                        "Цей купальник ідеально підходить для плавання, водних видів спорту, пляжних відпочинків або сесій фітнесу в басейні. Він комбінує в собі функціональність та стиль, дозволяючи вам виглядати і відчувати себе чудово.",
                        2000.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "17.webp");
        create(new ProductReadDto(
                        "Жіночий злитий купальник з вирезом",
                        "Купальник Nike Block Texture чорний - це елегантний та практичний вибір для жінок, які шукають спортивний купальник з якісним дизайном та зручністю носіння. Його модний вигляд і комфорт роблять його відмінним вибором для активних літніх відпочинків.",
                        3000.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "18.webp");
        create(new ProductReadDto(
                        "Чоловічі шорти для плавання",
                        "Ця модель шортів створена для комфорту як під час плавання в басейні, так і під час розваг на пляжі. Злегка вільний крій та матеріали, використані у виробництві, дозволяють не обмежувати рухи та відчувати себе комфортно.",
                        1900.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "19.webp");
        create(new ProductReadDto(
                        "Чоловічі плавальні шорти для волейболу NIKE",
                        "Чоловічі плавальні шорти для волейболу Nike Logo Solid 5\" чорні - це практичний та стильний вибір для тих, хто цінує комфорт та якість у спортивному одязі. Вони дозволять вам відчути себе впевнено та комфортно під час активних занять на воді.",
                        2900.,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "20.webp");
        create(new ProductReadDto(
                        "Футболка з чорним принтом",
                        "Використання передових технологій та відповідних технік при нанесенні принта на цю модель футболки дозволило зробити принт довговічним і незмивним, не змінюючи при цьому властивостей одягу.",
                        3500.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "21.webp");
        create(new ProductReadDto(
                        "Шорти жіночі",
                        "Тканина, що використовується в цій моделі шорт, відповідає за хорошу повітропроникність. Це дозволяє волозі у вигляді водяної пари виходити назовні, зберігаючи шкіру сухою навіть під час інтенсивної активності.",
                        1800.,
                        new BrandGetDto(-1, "Balmain"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "22.webp");
        create(new ProductReadDto(
                        "Шорти жіночі",
                        "Ці шорти мають коричневий колір зі стильними декоративними елементами та вишитими або надрукованими логотипами MANTO. Дизайн може бути різноманітним, включаючи графічні елементи, етнічні мотиви або ілюстрації, які характерні для Муай Тай.",
                        1600.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "23.webp");
        create(new ProductReadDto(
                        "Футболка чоловіча",
                        "Виготовлення цього унікального принта на цій моделі рашгарду з використанням правильних технік і передових технологій зробило принт довговічним і стійким до стирання, не змінюючи при цьому властивостей одягу.",
                        3400.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "24.webp");
        create(new ProductReadDto(
                        "Чоловічі шорти",
                        "Щоб гарантувати комфорт цієї моделі шортів, було враховано низку факторів. Щоб забезпечити стабільність виробу, основна увага була приділена створенню спеціальної конструкції та вибору правильної тканини.",
                        2300.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "25.webp");
        create(new ProductReadDto(
                        "Шорти рожеві жіночі",
                        "Ці шорти мають рожевий колір зі стильними декоративними елементами та вишитими або надрукованими логотипами MANTO. Дизайн може бути різноманітним, включаючи графічні елементи, етнічні мотиви або ілюстрації, які характерні для Муай Тай.",
                        1600.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "26.webp");
        create(new ProductReadDto(
                        "Футболка чоловіча",
                        "Футболка має класичний крій та світло-блакитний колір, що робить її неймовірно універсальною та легкою для поєднання з іншими предметами гардеробу. На футболці може бути стильний графічний друк чи вишивка, що надає їй індивідуальний характер.",
                        1050.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "27.webp");
        create(new ProductReadDto(
                        "Жіночі велошорти",
                        "Модель велошортів з інтегрованими лямками - це гарантія комфорту під час їзди на велосипеді. Крій цієї моделі означає, що шорти дуже добре сидять, не сповзають і немає необхідності носити ремінь, який міг би спричинити тиск при сидінні.",
                        5000.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "28.webp");
        create(new ProductReadDto(
                        "Жіноча Oversize футболка",
                        "Ця чорна футболка MANTO Oversize - це не лише зручний елемент гардеробу, але і модний акцент, що надає вашому образу стильний шарм та вишуканість.",
                        5000.,
                        new BrandGetDto(-1, "MANTO"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "29.webp");
        create(new ProductReadDto(
                        "Жіночі шорти",
                        "Жіночі шорти FILA у темно-зеленому кольорі (dark forest) - це модний та комфортний вибір для активних жінок, які цінують якість і стиль.",
                        1400.0,
                        new BrandGetDto(-1, "FILA"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "30.webp");
        create(new ProductReadDto(
                        "Жіночі шорти",
                        "Шорти мають вільний крій та комфортну посадку, що надає вам відмінну свободу рухів. Еластичний пояс зі шнурком дозволяє регулювати посадку зручним чином. Шорти мають яскравий абстрактний принт на темно-зеленому тлі, що надає їм вишуканий та модний вигляд.",
                        1400.0,
                        new BrandGetDto(-1, "FILA"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "31.webp");
        create(new ProductReadDto(
                        "Жіночі тренувальні шорти",
                        "Використання правильних матеріалів та конструкції дозволило зменшити вагу виробу, завдяки чому шорти надзвичайно легкі і не є додатковим баластом під час інтенсивних тренувань.",
                        2400.0,
                        new BrandGetDto(-1, "PUMA"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "32.webp");
        create(new ProductReadDto(
                        "Футболка Oversize",
                        "Футболка має великий, вільний фасон, що відомий як \"oversize\". Це означає, що вона має розширений крій та зручну, невимушену посадку. Такий фасон дозволяє вам вільно рухатися та надає вашому образу модний акцент.",
                        800.0,
                        new BrandGetDto(-1, "Гімн Свободи"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "33.jpg");
        create(new ProductReadDto(
                        "Футболка Oversize",
                        "Футболка oversize \"Жито\" ідеально підходить для повсякденного носіння, вечірок, фестивалів або інших заходів, де ви хочете виділятися і виглядати стильно. Ця футболка є виразом вашої індивідуальності і стилю, надаючи вам зручність та впевненість у будь-якому образі.",
                        800.0,
                        new BrandGetDto(-1, "Жито"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "34.jpg");
        create(new ProductReadDto(
                        "Футболка Oversize",
                        "Футболка FARFI оверсайз з дизайнерським принтом Tenerife. Модель ПРЕМІУМ якості зі щільної і водночас \"дихаючої\" бавовняної тканини. Футболка УНІСЕКС, пошита по новому крою з опущеними плечима.",
                        1900.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "35.jpg");
        create(new ProductReadDto(
                        "Футболка “Your Lie Kills”",
                        "Футболка FARFI вільного крою з колекції SS20, з дизайнерським принтом YOUR LIE KIILS. Модель виготовлена з високоякісної бавовни Heavy cotton, має прямий крій та круглий комір. Футболка оверсайз та унісекс.",
                        1700.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "36.webp");
        create(new ProductReadDto(
                        "Жіночий злитий купальник",
                        "Жіночий купальник Farfi з одним рукавом та асиметричним вирізом на шиї. Виконане з тканини з дизайнерським малюнком Deep dye, який наноситься на кожну деталь крою окремо, після чого кожна деталь вирізається вручну.",
                        1500.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "37.webp");
        create(new ProductReadDto(
                        "Футболка Oversize",
                        "Футболка FARFI оверсайз з дизайнерським принтом Tenerife. Модель ПРЕМІУМ якості зі щільної і водночас 'дихаючої' бавовняної тканини. Футболка УНІСЕКС, пошита по новому крою з опущеними плечима.",
                        2900.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "38.webp");
        create(new ProductReadDto(
                        "Жіночий злитий купальник",
                        "Жіночий купальник Farfi з одним рукавом та асиметричним вирізом на шиї. Виконане з тканини з дизайнерським малюнком Deep dye, який наноситься на кожну деталь крою окремо, після чого кожна деталь вирізається вручну.",
                        1500.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "39.webp");
        create(new ProductReadDto(
                        "Футболка Oversize",
                        "Футболка FARFI вільного крою з колекції SS21 з дизайнерським принтом ZUBKI. Модель виготовлена з високоякісної бавовни Heavy cotton, має прямий крій та круглий комір. Футболка оверсайз та унісекс.",
                        2900.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "40.webp");
        create(new ProductReadDto(
                        "Шорти чоловічі",
                        "Шорти Farfi з логотипом та дизайнерським принтом Makima. Модель, виконана з високоякісної бавовни, з двома накладними кишенями без застібок.",
                        1200.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "41.webp");
        create(new ProductReadDto(
                        "Жіночі тренувальні шорти",
                        "Використання правильних матеріалів та конструкції дозволило зменшити вагу виробу, завдяки чому шорти надзвичайно легкі і не є додатковим баластом під час інтенсивних тренувань.",
                        2400.0,
                        new BrandGetDto(-1, "GYMSHARK"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "42.webp");
        create(new ProductReadDto(
                        "Футболка жіноча",
                        "Матеріал, з якого виготовлено цей виріб, відповідає за належну повітропроникність. Це дозволяє водяній парі належним чином виводитися назовні, у зовнішні шари, зберігаючи шкіру сухою навіть під час інтенсивних навантажень.",
                        1050.0,
                        new BrandGetDto(-1, "FILA"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMEN",
                        null,
                        null)
                , "43.webp");
        create(new ProductReadDto(
                        "Футболка жіноча тренувальна",
                        "Відповідна конструкція та матеріали дозволили зменшити вагу футболки, завдяки чому виріб є надзвичайно легким і не додає зайвої ваги під час дуже інтенсивних тренувань.",
                        2000.0,
                        new BrandGetDto(-1, "FILA"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMEN",
                        null,
                        null)
                , "44.webp");
        create(new ProductReadDto(
                        "Жіночі тренувальні шорти",
                        "Якщо Ви належите до людей з чутливою шкірою, Вам варто обирати безшовний одяг. Така модель шорт не викликатиме натирання та подразнення, особливо під час тривалих та інтенсивних тренувань, а також характеризується більшою компресією та довговічністю.",
                        1678.0,
                        new BrandGetDto(-1, "GYMSHARK"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMEN",
                        null,
                        null)
                , "45.webp");
        create(new ProductReadDto(
                        "Чоловічі шорти",
                        "Шорти мають модний та аеродинамічний крій, призначений для комфортного руху. Їхня конструкція надає вільність рухів і забезпечує вам можливість виконувати різноманітні рухи. Шорти можуть мати вишивку або друк логотипу Pitbull West Coast та інших графічних елементів, що роблять їх впізнаваними як продукцію цього бренду.",
                        2300.0,
                        new BrandGetDto(-1, "PITBULL"),
                        new CategoryGetDto(-1, "Шорти"),
                        "MAN",
                        null,
                        null)
                , "46.webp");
        create(new ProductReadDto(
                        "Футболка жіноча",
                        "Футболка має класичний крій та відмінно сидить на жіночій фігурі. Вона може мати круглий або V-подібний виріз, що надає їй жіночність і вигідно підкреслює форми.",
                        2000.0,
                        new BrandGetDto(-1, "FILA"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "47.webp");
        create(new ProductReadDto(
                        "Топ жіночий тренувальний",
                        "Топ характеризується гарним приляганням до силуету, діючи як друга шкіра під час тренувань. Вибір одягу, що облягає фігуру, підвищує комфорт, оскільки він не обмежує рухів, краще відводить вологу і не викликає натирання.",
                        2000.0,
                        new BrandGetDto(-1, "GYMSHARK"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "48.webp");
        create(new ProductReadDto(
                        "Футболка жіноча",
                        "Футболка характеризується гарним обляганням силуету, діючи як друга шкіра. Вибір облягаючого одягу підвищує комфорт, оскільки він не обмежує рухів, краще відводить вологу і не викликає натирання.",
                        500.0,
                        new BrandGetDto(-1, "Highway"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "49.webp");
        create(new ProductReadDto(
                        "Футболка Phantom",
                        "Футболка FARFI оверсайз з дизайнерським принтом Phantom. Модель ПРЕМІУМ якості зі щільної і водночас \"дихаючої\" бавовняної тканини. Футболка УНІСЕКС, пошита по новому крою з опущеними плечима.",
                        1900.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "50.webp");
        create(new ProductReadDto(
                        "Футболка Phantom",
                        "Футболка FARFI вільного крою з колекції SS21 з дизайнерським принтом LAST SHOT. Модель виготовлена з високоякісної бавовни Heavy cotton, має прямий крій та круглий комір. Футболка оверсайз та унісекс.",
                        1900.0,
                        new BrandGetDto(-1, "FARFI"),
                        new CategoryGetDto(-1, "Футболка"),
                        "MAN",
                        null,
                        null)
                , "51.webp");
        create(new ProductReadDto(
                        "КОНТРАСТНІ СМУГАСТІ ШОРТИ",
                        "Шорти з еластичним поясом, ґудзиками та середньою посадкою.",
                        2400.0,
                        new BrandGetDto(-1, "ZARA"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "52.jpg");
        create(new ProductReadDto(
                        "Жіночі велошорти",
                        "Шорти мають аеродинамічний крій, який ідеально облягає фігуру, забезпечуючи оптимальну підтримку та свободу рухів під час кожного крутого повороту велосипеда. Вони можуть бути середньої довжини, закінчуючись близько середини стегон.",
                        909.0,
                        new BrandGetDto(-1, "GYMSARK"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "53.webp");
        create(new ProductReadDto(
                        "Жіночі велошорти сірі",
                        "Виготовлені з високоякісних матеріалів, які поєднують в собі бавовну та поліестер або еластан. Це надає шортам м'якість, еластичність і зносостійкість, роблячи їх ідеальними для активних видів спорту та повсякденного носіння.",
                        500.0,
                        new BrandGetDto(-1, "FILA"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "54.webp");
        create(new ProductReadDto(
                        "Жіночі велошорти чорні",
                        "Виготовлені з високоякісних матеріалів, які поєднують в собі бавовну та поліестер або еластан. Це надає шортам м'якість, еластичність і зносостійкість, роблячи їх ідеальними для активних видів спорту та повсякденного носіння.",
                        500.0,
                        new BrandGetDto(-1, "FILA"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "55.webp");
        create(new ProductReadDto(
                        "Жіночі тренувальні шорти",
                        "Якщо Ви належите до людей з чутливою шкірою, Вам варто обирати безшовний одяг. Така модель шорт не викликатиме натирання та подразнення, особливо під час тривалих та інтенсивних тренувань, а також характеризується більшою компресією та довговічністю.",
                        1678.0,
                        new BrandGetDto(-1, "GYMSHARK"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "56.webp");
        create(new ProductReadDto(
                        "Жіночі тренувальні шорти",
                        "Якщо Ви належите до людей з чутливою шкірою, Вам варто обирати безшовний одяг. Така модель шорт не викликатиме натирання та подразнення, особливо під час тривалих та інтенсивних тренувань, а також характеризується більшою компресією та довговічністю.",
                        1678.0,
                        new BrandGetDto(-1, "GYMSHARK"),
                        new CategoryGetDto(-1, "Шорти"),
                        "WOMAN",
                        null,
                        null)
                , "57.webp");
        create(new ProductReadDto(
                        "Жіноча футболка",
                        "Ця футболка має облягаючий крій, який підкреслює жіночні форми та забезпечує вільність рухів. Вона може мати круглий або V-подібний виріз, що надає їй спортивний та елегантний вигляд.",
                        1678.0,
                        new BrandGetDto(-1, "GYMSHARK"),
                        new CategoryGetDto(-1, "Футболка"),
                        "WOMAN",
                        null,
                        null)
                , "58.webp");
        create(new ProductReadDto(
                        "Жіночий суцільний купальник",
                        "Ця модель купальника має Х-подібний дизайн спини з бретелями, які перехрещуються на спині. Така конструкція звільняє лопатки, забезпечуючи комфортні рухи та краще підтримує бюст.",
                        1800.0,
                        new BrandGetDto(-1, "FUNKITA"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "59.webp");
        create(new ProductReadDto(
                        "Жіночий злитий купальник",
                        "Ця модель купальника має U-подібний виріз на спині. Особливо рекомендується жінкам більшої статури, оскільки така форма дозволяє легко одягати і знімати його через стегна і є дуже зручною.",
                        900.0,
                        new BrandGetDto(-1, "NIKE"),
                        new CategoryGetDto(-1, "Купальник"),
                        "WOMAN",
                        null,
                        null)
                , "60.webp");
        create(new ProductReadDto(
                "Купальник жіночий бікіні",
                "Ця модель купальника оснащена знімними чашками. Залежно від Ваших потреб, Ви можете використовувати їх для кращої підтримки грудей. Зняття чашок також дозволить купальнику швидше висохнути.",
                1800.0,
                new BrandGetDto(-1, "NIKE"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null)
                , "61.webp");
        create(new ProductReadDto(
                "Дитячий злитий купальник",
                "Ця модель купальника виготовлена з швидковисихаючої тканини, що означає, що вода виводиться назовні, де вона може швидко випаровуватися. Тому він підійде для занять водними видами спорту на відкритому повітрі.",
                900.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
            ), "62.webp");
        create(new ProductReadDto(
                "Дитячий злитий купальник",
                "Ця модель купальника виготовлена з швидковисихаючої тканини, що означає, що вода виводиться назовні, де вона може швидко випаровуватися. Тому він підійде для занять водними видами спорту на відкритому повітрі.",
                900.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
            ), "63.webp");
        create(new ProductReadDto(
                "Дитячий злитий купальник",
                "Ця модель купальника виготовлена з швидковисихаючої тканини, що означає, що вода виводиться назовні, де вона може швидко випаровуватися. Тому він підійде для занять водними видами спорту на відкритому повітрі.",
                1900.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
            ), "64.webp");
        create(new ProductReadDto(
                "Дитячий роздільний купальник",
                "Цей купальник має роздільний дизайн, що означає окремий верх та нижню частину. Верх може бути виготовлений у вигляді топа з тонкими бретелями або спортивної футболки з коротким рукавом. Нижня частина може мати шорти або спідничку.",
                1900.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
            ), "65.webp");
        create(new ProductReadDto(
                "Злитий купальник жіночий",
                "Купальник Speedo New Contour Eclipse у чорному кольорі - це надійний та модний вибір для жінок, які цінують якість, комфорт та стиль в одязі для плавання. Він додасть вам впевненості та забезпечить незабутні враження під час пляжних або басейнових пригод.",
                1900.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
            ), "66.webp");
        create(new ProductReadDto(
                "Злитий купальник жіночий з закритою спиною",
                "Купальник має закриту спину та високий воріт для максимальної гідродинаміки та підтримки спини. Його крій підкреслює фігуру, надаючи спортивний і стрункий вигляд. Виготовлений з ультра-легкої та високоеластичної тканини, яка вологостійка та швидко сохне. Цей матеріал має високий ступінь компресії, що підтримує м'язи під час плавання і забезпечує оптимальну гідродинаміку.",
                11900.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
        ), "67.webp");
        create(new ProductReadDto(
                "Злитий купальник жіночий",
                "Цей купальник ідеально підходить для плавання в басейні, на пляжі, а також для занять аквагімнастикою чи іншими водними видами спорту. Він забезпечує гарну гідродинаміку та зручність під час рухів у воді.",
                1105.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
        ), "68.webp");
        create(new ProductReadDto(
                "Злитий купальник жіночий",
                "Ця модель купальника має О-подібний виріз на спині. Такий крій дозволяє ефективно тренуватися. Круглий виріз відкриває лопатки і не обмежує рухи плечей.",
                2005.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
        ), "69.webp");
        create(new ProductReadDto(
                "Жіночий суцільний купальник",
                "Цей купальник має суцільний дизайн, який ідеально облягає фігуру, підкреслюючи її форми. Він може мати глибокий виріз на спині або специфічний дизайн спини, який надає йому стильний вигляд.",
                1200.0,
                new BrandGetDto(-1, "NIKE"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
        ), "70.webp");
        create(new ProductReadDto(
                "Чоловічі плавки",
                "У цій моделі плавок використовується просте і зручне регулювання за допомогою шнурка. Завдяки цьому Ви можете допасувати їх під індивідуальний обхват в поясі. Таким чином, плавальні шорти добре лежать і не зісковзують під час плавання.",
                2200.0,
                new BrandGetDto(-1, "ZARA"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "71.webp");
        create(new ProductReadDto(
                "Чоловічі плавки-боксери",
                "У цій моделі плавок використовується просте і зручне регулювання за допомогою шнурка. Завдяки цьому Ви можете допасувати їх під індивідуальний обхват в поясі. Таким чином, плавальні шорти добре лежать і не зісковзують під час плавання.",
                3200.0,
                new BrandGetDto(-1, "ZARA"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "72.webp");
        create(new ProductReadDto(
                "Чоловічі плавки",
                "Крій цієї моделі плавок, а також матеріали, використані у виробництві, роблять їх надзвичайно зручними. Вони забезпечують повний діапазон рухів ногами, тому Ваше тренування буде ефективним та приємним.",
                3200.0,
                new BrandGetDto(-1, "ZARA"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "73.webp");
        create(new ProductReadDto(
                "Чоловічі плавки",
                "Для виробництва цих плавок використовується матеріал, стійкий до шкідливого впливу хлору. Навіть тривале використання їх у басейні не вплине на їхню зносостійкість. Тож прослужать вони довго.",
                1000.0,
                new BrandGetDto(-1, "ZARA"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "74.webp");
        create(new ProductReadDto(
                "Чоловіча футболка для плавання",
                "Приємна і м'яка до тіла тканина футболки не тільки робить її більш комфортною в носінні, але й запобігає подразненню і натиранню, яким піддається шкіра при контакті з мокрим одягом.",
                2000.0,
                new BrandGetDto(-1, "ZARA"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "75.webp");
        create(new ProductReadDto(
                "Чоловіча футболка для плавання",
                "Тканина, з якої виготовлена футболка, характеризується дуже гарною розтяжністю та еластичністю. Вона природно прилягає до тіла і повторює його рухи під час фізичної активності.",
                2000.0,
                new BrandGetDto(-1, "ZARA"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "76.webp");
        create(new ProductReadDto(
                "Купальник суцільний жіночий",
                "Ця модель купальника має Х-подібний дизайн спини з бретелями, які перехрещуються на спині. Така конструкція звільняє лопатки, забезпечуючи комфортні рухи та краще підтримує бюст.",
                2000.0,
                new BrandGetDto(-1, "ARENA"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
        ), "77.webp");
        create(new ProductReadDto(
                "Чоловічі плавки",
                "Ця модель плавок має облягаючий крій, який підійде у будь-яких обставинах. Вони забезпечать комфорт під час занять плаванням, а також звичайних ігор у воді. Вони не сковують рухів і зручні.",
                1100.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "78.webp");
        create(new ProductReadDto(
                "Чоловічі плавки",
                "Ця модель плавок має облягаючий крій, який підійде у будь-яких обставинах. Вони забезпечать комфорт під час занять плаванням, а також звичайних ігор у воді. Вони не сковують рухів і зручні.",
                1600.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "MAN",
                null,
                null
        ), "79.webp");
        create(new ProductReadDto(
                "Злитий купальник жіночий",
                "Ця модель купальника має О-подібний виріз на спині. Такий крій дозволяє ефективно тренуватися. Круглий виріз відкриває лопатки і не обмежує рухи плечей.",
                2005.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
        ), "80.webp");
        create(new ProductReadDto(
                "Жіночий суцільний купальник",
                "Ця модель купальника призначена для тривалого використання. Купальник виготовлений з тканини з хорошою стійкістю до хлору. Завдяки цьому він прослужить довго, не втрачаючи своїх властивостей.",
                2005.0,
                new BrandGetDto(-1, "SPEEDO"),
                new CategoryGetDto(-1, "Купальник"),
                "WOMAN",
                null,
                null
        ), "81.webp");
        create(new ProductReadDto(
                "Жіночі шорти",
                "Ці зелені шорти - стильний і яскравий елемент вашого літнього гардеробу. Виготовлені з легкої та дихаючої тканини, вони ідеально підходять для теплих сезонів і забезпечують комфорт під час спекотних днів.",
                2100.0,
                new BrandGetDto(-1, "FILA"),
                new CategoryGetDto(-1, "Шорти"),
                "WOMAN",
                null,
                null
        ), "82.webp");
        create(new ProductReadDto(
                "Жіноча футболка",
                "Ця сіра футболка - відмінний базовий елемент вашого гардеробу. Вона виготовлена з якісної та м'якої тканини, що надає вам відчуття комфорту під час носіння. Сірий колір - класичний і універсальний, що дозволяє легко поєднувати цю футболку з різними стилями та кольорами.",
                1100.0,
                new BrandGetDto(-1, "GYMSHARK"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "83.webp");
        create(new ProductReadDto(
                "Жіночі шорти",
                "Ці шорти мають аеродинамічний крій та виготовлені з високоякісних матеріалів, які відзначаються легкістю та вентиляцією. Матеріали, з яких вони зроблені, дозволяють шкірі 'дихати', запобігають перегріванню та відводять вологу, що робить їх ідеальними для використання в умовах великої фізичної активності та високої вологості.",
                1200.0,
                new BrandGetDto(-1, "LASPORTIVA"),
                new CategoryGetDto(-1, "Шорти"),
                "WOMAN",
                null,
                null
        ), "84.webp");
        create(new ProductReadDto(
                "Чоловічі шорти",
                "Ці сірі шорти - це відмінний вибір для стильних та комфортних літніх образів. Виготовлені з легкої і дихаючої тканини, вони забезпечують комфорт і свіжість навіть у спекотний день.",
                1200.0,
                new BrandGetDto(-1, "FILA"),
                new CategoryGetDto(-1, "Шорти"),
                "MAN",
                null,
                null
        ), "85.webp");
        create(new ProductReadDto(
                "Жіноча футболка чорна",
                "Оновіть свій базовий образ за допомогою цієї чорної бавовняної футболки оверсайз Gucci. Цей виріб з короткими рукавами, прикрашений принтом з логотипом на грудях, що створює потертий вигляд і вишитою квіткою на спині, має невеликі надриви на шиї, що створюють ефект зношеності. Ідеальна річ, яка підкреслить ваш повсякденний образ.",
                3200.0,
                new BrandGetDto(-1, "GUCCI"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "86.webp");
        create(new ProductReadDto(
                "Жіноча футболка біла",
                "Оновіть свій базовий образ за допомогою цієї білої бавовняної футболки оверсайз Guess. Цей виріб з короткими рукавами, прикрашений принтом з логотипом на грудях, що створює потертий вигляд і вишитою квіткою на спині, має невеликі надриви на шиї, що створюють ефект зношеності. Ідеальна річ, яка підкреслить ваш повсякденний образ.",
                3200.0,
                new BrandGetDto(-1, "GUESS"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "87.webp");
        create(new ProductReadDto(
                "Жіноча футболка біла",
                "Оновіть свій базовий образ за допомогою цієї білої бавовняної футболки оверсайз Guess. Цей виріб з короткими рукавами, прикрашений принтом з логотипом на грудях, що створює потертий вигляд і вишитою квіткою на спині, має невеликі надриви на шиї, що створюють ефект зношеності. Ідеальна річ, яка підкреслить ваш повсякденний образ.",
                3200.0,
                new BrandGetDto(-1, "GUESS"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "88.webp");
        create(new ProductReadDto(
                "Жіноча футболка біла",
                "Оновіть свій базовий образ за допомогою цієї білої бавовняної футболки оверсайз Guess. Цей виріб з короткими рукавами, прикрашений принтом з логотипом на грудях, що створює потертий вигляд і вишитою квіткою на спині, має невеликі надриви на шиї, що створюють ефект зношеності. Ідеальна річ, яка підкреслить ваш повсякденний образ.",
                3200.0,
                new BrandGetDto(-1, "GUESS"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "89.webp");
        create(new ProductReadDto(
                "Жіноча футболка біла",
                "Ця футболка може бути чудовим доповненням до вашого гардеробу, надаючи йому нотку романтики і елегантності. Вона підходить як для повсякденних образів, так і для особливих випадків, надаючи вам стильний і модний вигляд.",
                3200.0,
                new BrandGetDto(-1, "GUESS"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "90.webp");
        create(new ProductReadDto(
                "Жіноча футболка біла",
                "Цей малюнок може бути виконаний у ніжних тонуваннях, створюючи враження легкості і ніжності. Малюнок може бути нанесений з використанням високоякісних технологій друку або вишивки, що гарантує його тривалість і яскравість кольорів.",
                3200.0,
                new BrandGetDto(-1, "GUESS"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "91.webp");
        create(new ProductReadDto(
                "Футболка жіноча",
                "Точний розмір допоможемо підібрати, за Вашим зростанням та вагою\n" +
                        "Матеріал: бавовна, натуральні волокна\n" +
                        "Зображення: фабричний друк, не тьмяніє в процесі прання",
                1990.0,
                new BrandGetDto(-1, "NIKE"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "92.webp");
        create(new ProductReadDto(
                "Футболка жіноча",
                "Шорти виготовлені зі спеціальних матеріалів, які швидко сохнуть, що забезпечує високий рівень комфорту та робить їх ідеальними для тривалих, інтенсивних тренувань.",
                1990.0,
                new BrandGetDto(-1, "NIKE"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "93.webp");
        create(new ProductReadDto(
                "Жіночі тренувальні шорти зелені",
                "Якщо Ви належите до людей з чутливою шкірою, Вам варто обирати безшовний одяг. Така модель шорт не викликатиме натирання та подразнення, особливо під час тривалих та інтенсивних тренувань, а також характеризується більшою компресією та довговічністю.",
                1678.0,
                new BrandGetDto(-1, "GYMSHARK"),
                new CategoryGetDto(-1, "Шорти"),
                "WOMAN",
                null,
                null
        ), "94.webp");
        create(new ProductReadDto(
                "Футболка жіноча",
                "Чорний колір виглядає стильно та універсально, дозволяючи легко комбінувати його з іншими елементами вашого спортивного гардеробу. Ця модель має модний крій, який підкреслить ваші форми.",
                2490.0,
                new BrandGetDto(-1, "GYMSHARK"),
                new CategoryGetDto(-1, "Футболка"),
                "WOMAN",
                null,
                null
        ), "95.webp");
        create(new ProductReadDto(
                "Чоловіча футболка",
                "Лаконічний чорний колір футболки додає їй елегантності і універсальності. Футболка може мати вишивку або принт із символікою бренду FILA на передній частині або на рукаві, що додає їй оригінальності та виразності.",
                3000.0,
                new BrandGetDto(-1, "FILA"),
                new CategoryGetDto(-1, "Футболка"),
                "MAN",
                null,
                null
        ), "96.webp");
        create(new ProductReadDto(
                "Чоловіча футболка чорна",
                "Чорний колір футболки є універсальним і легко поєднується з іншими кольорами та стилями. Ця футболка може бути відмінним вибором для активного спорту, прогулянок чи повсякденного використання, надаючи вам спортивний і стильний вигляд. Вона також може мати вільний або облягаючий крій, залежно від вашого вибору та комфорту.",
                3000.0,
                new BrandGetDto(-1, "FILA"),
                new CategoryGetDto(-1, "Футболка"),
                "MAN",
                null,
                null
        ), "97.webp");
        create(new ProductReadDto(
                "Чоловіча футболка біла",
                "Білий колір футболки дуже універсальний і може бути відмінним вибором для будь-якого стилю або події. Ця футболка може бути як частиною спортивного, так і повсякденного образу, додаючи йому вишуканості та бездоганності.",
                3000.0,
                new BrandGetDto(-1, "FILA"),
                new CategoryGetDto(-1, "Футболка"),
                "MAN",
                null,
                null
        ), "98.webp");
        create(new ProductReadDto(
                "Жіночі тренувальні шорти жовті",
                "Якщо Ви належите до людей з чутливою шкірою, Вам варто обирати безшовний одяг. Така модель шорт не викликатиме натирання та подразнення, особливо під час тривалих та інтенсивних тренувань, а також характеризується більшою компресією та довговічністю.",
                1678.0,
                new BrandGetDto(-1, "GYMSHARK"),
                new CategoryGetDto(-1, "Шорти"),
                "WOMAN",
                null,
                null
        ), "99.webp");
        create(new ProductReadDto(
                "Жіночі тренувальні шорти сині",
                "Якщо Ви належите до людей з чутливою шкірою, Вам варто обирати безшовний одяг. Така модель шорт не викликатиме натирання та подразнення, особливо під час тривалих та інтенсивних тренувань.",
                1678.0,
                new BrandGetDto(-1, "GYMSHARK"),
                new CategoryGetDto(-1, "Шорти"),
                "WOMAN",
                null,
                null
        ), "100.webp");
    }

    public void create(ProductReadDto dto, String img) {
        int[] a = {1, 2, 3, 4, 5, 6};
        for (int i = 0; i < 6; i++) {
            int t = (int) (Math.random() * 6);
            int temp = a[t];
            a[t] = a[i];
            a[i] = temp;
        }

        List<SizeReadDto> l = new ArrayList<>();
        for (int i = 0; i < (int) (Math.random() * 4) + 2; i++) {
            l.add(new SizeReadDto(a[i], (int) (Math.random() * 10) + 1));
        }
        dto.setSize(l);

        if (dto.getGender().equals("MEN")) dto.setGender("MAN");
        if (dto.getGender().equals("WOMEN")) dto.setGender("WOMAN");

        Product product = mapFrom(dto);
        product.setCreatedAt(Instant.now());

        product.setBrand(createOrGetBrand(
                dto.getBrand().getId(), dto.getBrand().getName()
        ));
        product.setCategory(createOrGetCategory(
                dto.getCategory().getId(), dto.getCategory().getName()
        ));

        product.setPhoto(img);

        productsRepository.save(product);

        Map<Integer, Integer> storageList = dto.getSize().stream()
                .collect(Collectors.groupingBy(SizeReadDto::getId, Collectors.summingInt(SizeReadDto::getCount)));

        for (Map.Entry<Integer, Integer> storageDto : storageList.entrySet()) {
            Size size = sizesRepository.getReferenceById(storageDto.getKey());
            Storage storage = Storage.builder()
                    .product(product)
                    .size(size)
                    .count(storageDto.getValue())
                    .build();
            storagesRepository.save(storage);
        }
    }

    private Brand createOrGetBrand(Integer id, String name) {
        if (id != -1) {
            return brandsRepository.getReferenceById(id);
        }

        Optional<Brand> temp = brandsRepository.findBrandByName(name);
        return temp.orElseGet(() -> brandsRepository.save(new Brand(name)));

    }

    private Category createOrGetCategory(Integer id, String name) {
        if (id != -1) {
            return categoriesRepository.getReferenceById(id);
        }

        Optional<Category> temp = categoriesRepository.findCategoriesByName(name);
        return temp.orElseGet(() -> categoriesRepository.save(new Category(name)));
    }

    private Product mapFrom(ProductReadDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription() == null ? "" : dto.getDescription())
                .price(dto.getPrice())
                .gender(Gender.valueOf(dto.getGender()))
                .build();
    }
}
