package com.kingleadsw.ysm.oss;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/27 14:19
 * @Description:

@Service
@Log4j2
public class DefaultFileStorage implements  IFileStorage {

    @Autowired
    OSSClient ossClient;

    @Value("${oss.bucket}")
    private  String bucket;

    public OssResult ossUpload(String key, InputStream inputStream, ObjectMetadata metadata)
    {
        OssResult result = null;
        try
        {

            PutObjectResult putObjectResult = this.ossClient.putObject(bucket, key, inputStream, metadata);
            result = new OssResult(putObjectResult.getETag());

            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            String url = ossClient.generatePresignedUrl(bucket, key, expiration).toString();

            result.setUri(url);

            result.setSize(metadata.getContentLength());
            result.setBucket(bucket);
            result.setEndpoint(this.ossClient.getEndpoint().toString());
        }
        catch (OSSException | ClientException e)
        {
            log.error("上传文件失败", e);
            throw new NotfoundException(Integer.valueOf(500007));
        }
        return result;
    }

    protected ObjectMetadata createDefaultObjectMetadata(MultipartFile file)
    {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        return metadata;
    }

    @Override
    public OssResult upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        OssResult ossResult = null;
        try
        {
             ossResult = ossUpload(Sequences.get32UUID().substring(0,5)+fileName, file.getInputStream(),
                    createDefaultObjectMetadata(file));
        }
        catch (IOException e)
        {
            log.error("上传文件失败", e);
            throw new NotfoundException(Integer.valueOf(500007));
        }


        ossResult.setName(fileName);
        ossResult.setOriginalName(file.getName());


        return ossResult;
    }


    @Override
    public OssResult upload(File file) {
        String fileName = file.getName();
        OssResult ossFile = null;
        try
        {
            ObjectMetadata om = new ObjectMetadata();
            ossFile = ossUpload(fileName, new FileInputStream(file), om);
        }
        catch (IOException e)
        {
            log.error("上传失败", e);
            throw new NotfoundException(Integer.valueOf(500007));
        }
        if (ossFile.getETag() == null) {
            return null;
        }
        ossFile.setName(fileName);
        ossFile.setOriginalName(file.getName());

        return ossFile;
    }
}
 */