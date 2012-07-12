
select * from tbl_sp_location into outfile '/tmp/location.csv' fields terminated by ',' optionally enclosed by '' escaped by '' lines terminated by '\n';

mysql -usp -pliuyafei sp_endpoint -h42.121.64.116 -B -e "select * from tbl_sp_location`;" | sed 's/\t/","/g;s/^/"/;s/$/"/;s/\n//g' > mysql_exported_table.csv 