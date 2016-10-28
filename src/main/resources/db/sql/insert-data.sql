INSERT INTO SYMBOLS (ID,NAME)  VALUES (1,'SPY');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (2,'EEM');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (3,'DXJ');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (4,'GDXJ');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (5,'JNUG');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (6,'USO');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (7,'VXX');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (8,'UVXY');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (9,'TLT');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (10,'YCS');
INSERT INTO SYMBOLS (ID,NAME)  VALUES (11,'EUO');

INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (1,1,'com.alon_gazit.strategy.ShortStrategy','numOfShortDays=40,numOfStopDays=20');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (2,2,'com.alon_gazit.strategy.ShortStrategy','numOfShortDays=40,numOfStopDays=20');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (3,3,'com.alon_gazit.strategy.ShortStrategy','numOfShortDays=40,numOfStopDays=20');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (4,4,'com.alon_gazit.strategy.LongStrategy','numOfLongDays=20,numOfStopDays=10');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (5,5,'com.alon_gazit.strategy.LongStrategy','numOfLongDays=20,numOfStopDays=10');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (6,6,'com.alon_gazit.strategy.ShortStrategy','numOfShortDays=20,numOfStopDays=10');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (7,7,'com.alon_gazit.strategy.LongStrategy','numOfLongDays=20,numOfStopDays=5');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (8,8,'com.alon_gazit.strategy.LongStrategy','numOfLongDays=20,numOfStopDays=5');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (9,9,'com.alon_gazit.strategy.ShortStrategy','numOfShortDays=40,numOfStopDays=20');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (10,10,'com.alon_gazit.strategy.LongStrategy','numOfLongDays=40,numOfStopDays=20');
INSERT INTO STRATEGIES (ID,STOCK_ID,STRATEGY_CLASS,CLASS_PARAMS) VALUES
  (11,11,'com.alon_gazit.strategy.LongStrategy','numOfLongDays=40,numOfStopDays=20');

INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (1,1,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (2,2,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (3,3,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (4,4,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (5,5,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (6,6,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (7,7,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (8,8,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (9,9,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (10,10,'com.alon_gazit.risk.BasicRiskManagement','');
INSERT INTO RISK_MANAGEMENT (ID,STOCK_ID,RISK_MANAGEMENT_CLASS,CLASS_PARAMS) VALUES
  (11,11,'com.alon_gazit.risk.BasicRiskManagement','');
