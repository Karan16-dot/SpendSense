INSERT INTO categories
(id, name, icon, color, type, is_default, user_id, created_at, updated_at)
VALUES

    (gen_random_uuid(),'Food','restaurant','#FF9800','EXPENSE',TRUE,NULL,NOW(),NOW()),

    (gen_random_uuid(),'Travel','flight','#2196F3','EXPENSE',TRUE,NULL,NOW(),NOW()),

    (gen_random_uuid(),'Shopping','shopping_bag','#9C27B0','EXPENSE',TRUE,NULL,NOW(),NOW()),

    (gen_random_uuid(),'Bills','receipt_long','#F44336','EXPENSE',TRUE,NULL,NOW(),NOW()),

    (gen_random_uuid(),'Entertainment','movie','#673AB7','EXPENSE',TRUE,NULL,NOW(),NOW()),

    (gen_random_uuid(),'Healthcare','medical_services','#009688','EXPENSE',TRUE,NULL,NOW(),NOW()),

    (gen_random_uuid(),'Salary','payments','#4CAF50','INCOME',TRUE,NULL,NOW(),NOW()),

    (gen_random_uuid(),'Freelancing','laptop','#3F51B5','INCOME',TRUE,NULL,NOW(),NOW());